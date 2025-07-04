package com.eightsidedsquare.zine.client.block.model;

import net.fabricmc.fabric.api.client.model.loading.v1.CompositeBlockStateModel;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.render.model.SimpleBlockStateModel;
import net.minecraft.client.render.model.WeightedBlockStateModel;
import net.minecraft.client.render.model.json.ModelVariant;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;

import java.util.List;

public final class BlockStateModels {

    /**
     * Creates a {@link SimpleBlockStateModel.Unbaked} for the given model variant
     */
    public static SimpleBlockStateModel.Unbaked simple(ModelVariant variant) {
        return new SimpleBlockStateModel.Unbaked(variant);
    }

    /**
     * Creates a {@link SimpleBlockStateModel.Unbaked} for the given model identifier
     */
    public static SimpleBlockStateModel.Unbaked simple(Identifier modelId) {
        return simple(new ModelVariant(modelId));
    }

    /**
     * Creates a {@link WeightedBlockStateModel.Unbaked} for the given unbaked block state model pool
     */
    public static WeightedBlockStateModel.Unbaked weighted(Pool<BlockStateModel.Unbaked> entries) {
        return new WeightedBlockStateModel.Unbaked(entries);
    }

    /**
     * Creates a {@link WeightedBlockStateModel.Unbaked} for the given unbaked block state model pool builder
     */
    public static WeightedBlockStateModel.Unbaked weighted(Pool.Builder<BlockStateModel.Unbaked> entriesBuilder) {
        return weighted(entriesBuilder.build());
    }

    /**
     * Creates a {@link WeightedBlockStateModel.Unbaked} for the given unbaked block state models
     */
    public static WeightedBlockStateModel.Unbaked weighted(BlockStateModel.Unbaked... models) {
        Pool.Builder<BlockStateModel.Unbaked> entriesBuilder = Pool.builder();
        for (BlockStateModel.Unbaked model : models) {
            entriesBuilder.add(model);
        }
        return weighted(entriesBuilder);
    }

    /**
     * Creates a {@link CompositeBlockStateModel.Unbaked} for the given unbaked block state models
     */
    public static CompositeBlockStateModel.Unbaked composite(List<BlockStateModel.Unbaked> models) {
        return CompositeBlockStateModel.Unbaked.of(models);
    }

    /**
     * Creates a {@link CompositeBlockStateModel.Unbaked} for the given unbaked block state models
     */
    public static CompositeBlockStateModel.Unbaked composite(BlockStateModel.Unbaked... models) {
        return composite(List.of(models));
    }

    /**
     * Creates a {@link ConnectedBlockStateModel.Unbaked}
     * @param parent model id of the parent block model
     * @param baseName texture id base for the model's connected pattern textures
     * @param fancy {@code true} for adding edges based on blocks in front of a face
     */
    public static ConnectedBlockStateModel.Unbaked connected(Identifier parent, Identifier baseName, boolean fancy) {
        return new ConnectedBlockStateModel.Unbaked(parent, baseName, fancy);
    }

    /**
     * Creates a {@link ConnectedBlockStateModel.Unbaked}
     * @param baseName texture id base for the model's connected pattern textures
     * @param fancy {@code true} for adding edges based on blocks in front of a face
     */
    public static ConnectedBlockStateModel.Unbaked connected(Identifier baseName, boolean fancy) {
        return connected(Identifier.ofVanilla("block/block"), baseName, fancy);
    }

    /**
     * Creates a {@link TessellatingBlockStateModel.Unbaked}
     * @param parent model id of the parent block model
     * @param texture id of the texture
     * @param particleTexture id of the particle texture
     * @param size the number of blocks that the texture tessellates across
     */
    public static TessellatingBlockStateModel.Unbaked tessellating(Identifier parent, Identifier texture, Identifier particleTexture, int size) {
        return new TessellatingBlockStateModel.Unbaked(parent, texture, particleTexture, size);
    }

    /**
     * Creates a {@link TessellatingBlockStateModel.Unbaked}
     * @param parent model id of the parent block model
     * @param texture id of the texture
     * @param size the number of blocks that the texture tessellates across
     */
    public static TessellatingBlockStateModel.Unbaked tessellating(Identifier parent, Identifier texture, int size) {
        return new TessellatingBlockStateModel.Unbaked(parent, texture, size);
    }

    /**
     * Creates a {@link TessellatingBlockStateModel.Unbaked}
     * @param texture id of the texture
     * @param size the number of blocks that the texture tessellates across
     */
    public static TessellatingBlockStateModel.Unbaked tessellating(Identifier texture, int size) {
        return new TessellatingBlockStateModel.Unbaked(Identifier.ofVanilla("block/block"), texture, size);
    }

    private BlockStateModels() {
    }

}
