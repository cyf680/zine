package com.eightsidedsquare.zine.client.data;

import net.minecraft.block.Block;
import net.minecraft.client.data.BlockStateVariantMap;
import net.minecraft.client.data.MultipartBlockModelDefinitionCreator;
import net.minecraft.client.data.VariantsBlockModelDefinitionCreator;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.render.model.json.BlockModelDefinition;
import net.minecraft.client.render.model.json.WeightedVariant;

/**
 * Helper class for instantiating different types of {@link net.minecraft.client.data.BlockModelDefinitionCreator} as their names tend to be verbose
 */
public final class BlockModelDefinitions {

    /**
     * Creates a {@link VariantsBlockModelDefinitionCreator.Empty} for the given block
     */
    public static VariantsBlockModelDefinitionCreator.Empty variants(Block block) {
        return VariantsBlockModelDefinitionCreator.of(block);
    }

    /**
     * Creates a {@link VariantsBlockModelDefinitionCreator} for the given block
     * @param map the block state variant map of weighted variants to apply
     */
    public static VariantsBlockModelDefinitionCreator variants(Block block, BlockStateVariantMap<WeightedVariant> map) {
        return variants(block).with(map);
    }

    /**
     * Creates a {@link VariantsBlockModelDefinitionCreator} for the given block
     * @param variant the default variant
     */
    public static VariantsBlockModelDefinitionCreator variants(Block block, WeightedVariant variant) {
        return VariantsBlockModelDefinitionCreator.of(block, variant);
    }

    /**
     * Creates a {@link MultipartBlockModelDefinitionCreator} for the given block
     */
    public static MultipartBlockModelDefinitionCreator multipart(Block block) {
        return MultipartBlockModelDefinitionCreator.create(block);
    }

    /**
     * Creates a {@link CustomVariantsBlockModelDefinitionCreator} for the given block
     * @apiNote Unlike {@link VariantsBlockModelDefinitionCreator}, this accepts unbaked block state models
     */
    public static CustomVariantsBlockModelDefinitionCreator.Empty customVariants(Block block) {
        return CustomVariantsBlockModelDefinitionCreator.create(block);
    }

    /**
     * Creates a {@link CustomVariantsBlockModelDefinitionCreator} for the given block
     * @param map the block state variant map of unbaked block state models to apply
     * @apiNote Unlike {@link VariantsBlockModelDefinitionCreator}, this accepts unbaked block state models
     */
    public static CustomVariantsBlockModelDefinitionCreator customVariants(Block block, BlockStateVariantMap<BlockStateModel.Unbaked> map) {
        return CustomVariantsBlockModelDefinitionCreator.create(block, map);
    }

    /**
     * Creates a {@link CustomVariantsBlockModelDefinitionCreator} for the given block
     * @param model the default unbaked block state model
     * @apiNote Unlike {@link VariantsBlockModelDefinitionCreator}, this accepts unbaked block state models
     */
    public static CustomVariantsBlockModelDefinitionCreator customVariants(Block block, BlockStateModel.Unbaked model) {
        return CustomVariantsBlockModelDefinitionCreator.create(block, model);
    }

    /**
     * Creates a {@link CustomMultipartBlockModelDefinitionCreator} for the given block
     * @apiNote Unlike {@link MultipartBlockModelDefinitionCreator}, this accepts unbaked block state models
     */
    public static CustomMultipartBlockModelDefinitionCreator customMultipart(Block block) {
        return CustomMultipartBlockModelDefinitionCreator.create(block);
    }

    /**
     * Creates a {@link DirectBlockModelDefinitionCreator} for the given block
     * @param definition the block model definition that will be created
     */
    public static DirectBlockModelDefinitionCreator direct(Block block, BlockModelDefinition definition) {
        return DirectBlockModelDefinitionCreator.create(block, definition);
    }

    /**
     * Creates a {@link DirectBlockModelDefinitionCreator} for the given block
     * @param variants block model variants for the block model definition that will be created
     */
    public static DirectBlockModelDefinitionCreator direct(Block block, BlockModelDefinition.Variants variants) {
        return DirectBlockModelDefinitionCreator.create(block, variants);
    }

    /**
     * Creates a {@link DirectBlockModelDefinitionCreator} for the given block
     * @param multipart multipart block models for the block model definition that will be created
     */
    public static DirectBlockModelDefinitionCreator direct(Block block, BlockModelDefinition.Multipart multipart) {
        return DirectBlockModelDefinitionCreator.create(block, multipart);
    }

    private BlockModelDefinitions() {
    }

}
