package com.eightsidedsquare.zine.mixin.client;

import com.eightsidedsquare.zine.client.rendering.ShaderUniformRegistry;
import com.eightsidedsquare.zine.client.rendering.ShaderUniformRegistryImpl;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.VertexFormat;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.client.gl.GlUniform;
import net.minecraft.client.gl.ShaderProgram;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(ShaderProgram.class)
public abstract class ShaderProgramMixin {

    @Shadow public abstract @Nullable GlUniform getUniform(String name);

    @Unique
    private final Map<GlUniform, ShaderUniformRegistry.Initializer> uniformInitializers = new Object2ObjectArrayMap<>();
    @Unique
    private final ShaderUniformRegistryImpl.ContextImpl context = new ShaderUniformRegistryImpl.ContextImpl();

    @Inject(method = "set", at = @At("TAIL"))
    private void zine$setUniforms(List<RenderPipeline.UniformDescription> uniforms, List<String> samplers, CallbackInfo ci) {
        this.uniformInitializers.clear();
        for(Map.Entry<String, ShaderUniformRegistry.Initializer> entry : ShaderUniformRegistryImpl.INITIALIZERS.entrySet()) {
            GlUniform uniform = this.getUniform(entry.getKey());
            if(uniform != null) {
                this.uniformInitializers.put(uniform, entry.getValue());
            }
        }
    }

    @Inject(method = "initializeUniforms", at = @At("TAIL"))
    private void zine$initializeCustomUniforms(VertexFormat.DrawMode drawMode, Matrix4f viewMatrix, Matrix4f projectionMatrix, float screenWidth, float screenHeight, CallbackInfo ci) {
        this.context.set(drawMode, viewMatrix, projectionMatrix, screenWidth, screenHeight);
        for(Map.Entry<GlUniform, ShaderUniformRegistry.Initializer> entry : this.uniformInitializers.entrySet()) {
            this.context.setUniform(entry.getKey());
            entry.getValue().initialize(this.context);
        }
    }

}
