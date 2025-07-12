package com.eightsidedsquare.zine.client.block;

import net.fabricmc.fabric.api.client.model.loading.v1.CustomUnbakedBlockStateModel;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableMesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.BlockStateModel;

public interface CustomMeshUnbakedBlockStateModel extends CustomUnbakedBlockStateModel {

    int getMeshCount();

    BlockStateModel bake(MutableMesh builder, QuadEmitter emitter, Mesh[] meshes, Baker baker);

    @Override
    default BlockStateModel bake(Baker baker) {
        MutableMesh builder = Renderer.get().mutableMesh();
        QuadEmitter emitter = builder.emitter();
        Mesh[] meshes = new Mesh[this.getMeshCount()];
        return this.bake(builder, emitter, meshes, baker);
    }

    @Override
    default void resolve(Resolver resolver) {

    }
}
