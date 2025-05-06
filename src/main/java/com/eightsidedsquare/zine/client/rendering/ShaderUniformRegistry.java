package com.eightsidedsquare.zine.client.rendering;

import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.gl.GlUniform;
import org.joml.Matrix4f;

public interface ShaderUniformRegistry {

    ShaderUniformRegistry INSTANCE = new ShaderUniformRegistryImpl();

    void register(String name, Initializer initializer);

    interface Context {
        GlUniform uniform();

        VertexFormat.DrawMode drawMode();

        Matrix4f viewMatrix();

        Matrix4f projectionMatrix();

        float screenWidth();

        float screenHeight();
    }

    @FunctionalInterface
    interface Initializer {
        void initialize(Context context);
    }

}
