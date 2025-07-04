package com.eightsidedsquare.zine.client.block.model;

import net.minecraft.block.Block;
import net.minecraft.client.data.ModelIds;
import net.minecraft.client.render.model.json.ModelVariant;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;

public final class BlockModelParts {

    public static ModelVariant variant(Identifier modelId, ModelVariant.ModelState modelState) {
        return new ModelVariant(modelId, modelState);
    }

    public static ModelVariant variant(Identifier modelId) {
        return new ModelVariant(modelId);
    }

    public static ModelVariant variant(Block block) {
        return variant(ModelIds.getBlockModelId(block));
    }

    public static ModelVariant variant(Block block, String suffix) {
        return variant(ModelIds.getBlockSubModelId(block, suffix));
    }

    public static WeightedVariant weighted(Pool<ModelVariant> variants) {
        return new WeightedVariant(variants);
    }

    public static WeightedVariant weighted(Pool.Builder<ModelVariant> variantsBuilder) {
        return weighted(variantsBuilder.build());
    }

    public static WeightedVariant weighted(ModelVariant... variants) {
        Pool.Builder<ModelVariant> variantsBuilder = Pool.builder();
        for (ModelVariant variant : variants) {
            variantsBuilder.add(variant);
        }
        return weighted(variantsBuilder);
    }

    public static WeightedVariant weighted(Identifier... modelIds) {
        Pool.Builder<ModelVariant> variantsBuilder = Pool.builder();
        for (Identifier modelId : modelIds) {
            variantsBuilder.add(variant(modelId));
        }
        return weighted(variantsBuilder);
    }

    public static WeightedVariant weighted(Block block) {
        return weighted(ModelIds.getBlockModelId(block));
    }

    public static WeightedVariant weighted(Block block, String suffix) {
        return weighted(ModelIds.getBlockSubModelId(block, suffix));
    }
    
    private BlockModelParts() {
    }
    
}
