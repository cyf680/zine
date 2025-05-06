package com.eightsidedsquare.zine.client.rendering;

import com.mojang.blaze3d.vertex.VertexFormat;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.client.gl.GlUniform;
import net.minecraft.client.gl.ShaderProgram;
import org.joml.Matrix4f;

import java.util.Map;

public final class ShaderUniformRegistryImpl implements ShaderUniformRegistry {
    ShaderUniformRegistryImpl() {
    }

    public static final Map<String, Initializer> INITIALIZERS = new Object2ObjectOpenHashMap<>();

    @Override
    public void register(String name, Initializer initializer) {
        INITIALIZERS.put(name, initializer);
        ShaderProgram.PREDEFINED_UNIFORMS.add(name);
    }

    public static class ContextImpl implements Context {

        private GlUniform uniform;
        private VertexFormat.DrawMode drawMode;
        private Matrix4f viewMatrix;
        private Matrix4f projectionMatrix;
        private float screenWidth;
        private float screenHeight;

        public void setUniform(GlUniform uniform) {
            this.uniform = uniform;
        }

        public void set(VertexFormat.DrawMode drawMode, Matrix4f viewMatrix, Matrix4f projectionMatrix, float screenWidth, float screenHeight) {
            this.drawMode = drawMode;
            this.viewMatrix = viewMatrix;
            this.projectionMatrix = projectionMatrix;
            this.screenWidth = screenWidth;
            this.screenHeight = screenHeight;
        }

        @Override
        public GlUniform uniform() {
            return this.uniform;
        }

        @Override
        public VertexFormat.DrawMode drawMode() {
            return this.drawMode;
        }

        @Override
        public Matrix4f viewMatrix() {
            return this.viewMatrix;
        }

        @Override
        public Matrix4f projectionMatrix() {
            return this.projectionMatrix;
        }

        @Override
        public float screenWidth() {
            return this.screenWidth;
        }

        @Override
        public float screenHeight() {
            return this.screenHeight;
        }
    }
}
