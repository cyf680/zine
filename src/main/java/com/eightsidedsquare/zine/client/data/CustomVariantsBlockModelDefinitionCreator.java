package com.eightsidedsquare.zine.client.data;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.client.data.BlockModelDefinitionCreator;
import net.minecraft.client.data.BlockStateVariantMap;
import net.minecraft.client.data.PropertiesMap;
import net.minecraft.client.data.VariantsBlockModelDefinitionCreator;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.render.model.json.BlockModelDefinition;
import net.minecraft.state.property.Property;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.UnaryOperator;

public class CustomVariantsBlockModelDefinitionCreator implements BlockModelDefinitionCreator {

    private final Block block;
    private final Map<PropertiesMap, BlockStateModel.Unbaked> variants;
    private final Set<Property<?>> definedProperties;

    private CustomVariantsBlockModelDefinitionCreator(Block block, Map<PropertiesMap, BlockStateModel.Unbaked> variants, Set<Property<?>> definedProperties) {
        this.block = block;
        this.variants = variants;
        this.definedProperties = definedProperties;
    }

    public CustomVariantsBlockModelDefinitionCreator coordinate(BlockStateVariantMap<UnaryOperator<BlockStateModel.Unbaked>> operatorMap) {
        Set<Property<?>> properties = VariantsBlockModelDefinitionCreator.validateAndAddProperties(this.definedProperties, this.block, operatorMap);
        ImmutableMap.Builder<PropertiesMap, BlockStateModel.Unbaked> builder = ImmutableMap.builder();
        for (Map.Entry<PropertiesMap, BlockStateModel.Unbaked> entry : this.variants.entrySet()) {
            for (Map.Entry<PropertiesMap, UnaryOperator<BlockStateModel.Unbaked>> operatorEntry : operatorMap.getVariants().entrySet()) {
                builder.put(entry.getKey().copyOf(operatorEntry.getKey()), operatorEntry.getValue().apply(entry.getValue()));
            }
        }
        return new CustomVariantsBlockModelDefinitionCreator(this.block, builder.build(), properties);
    }

    public CustomVariantsBlockModelDefinitionCreator apply(UnaryOperator<BlockStateModel.Unbaked> operator) {
        ImmutableMap.Builder<PropertiesMap, BlockStateModel.Unbaked> builder = ImmutableMap.builder();
        for (Map.Entry<PropertiesMap, BlockStateModel.Unbaked> entry : this.variants.entrySet()) {
            builder.put(entry.getKey(), operator.apply(entry.getValue()));
        }
        return new CustomVariantsBlockModelDefinitionCreator(this.block, builder.build(), this.definedProperties);
    }

    public static CustomVariantsBlockModelDefinitionCreator.Empty create(Block block) {
        return new CustomVariantsBlockModelDefinitionCreator.Empty(block);
    }

    public static CustomVariantsBlockModelDefinitionCreator create(Block block, BlockStateVariantMap<BlockStateModel.Unbaked> map) {
        Set<Property<?>> properties = VariantsBlockModelDefinitionCreator.validateAndAddProperties(Set.of(), block, map);
        return new CustomVariantsBlockModelDefinitionCreator(block, map.getVariants(), properties);
    }

    public static CustomVariantsBlockModelDefinitionCreator create(Block block, BlockStateModel.Unbaked model) {
        return new CustomVariantsBlockModelDefinitionCreator(block, Map.of(PropertiesMap.EMPTY, model), Set.of());
    }

    @Override
    public Block getBlock() {
        return this.block;
    }

    @Override
    public BlockModelDefinition createBlockModelDefinition() {
        Map<String, BlockStateModel.Unbaked> models = new HashMap<>();
        for (Map.Entry<PropertiesMap, BlockStateModel.Unbaked> entry : this.variants.entrySet()) {
            models.put(entry.getKey().asString(), entry.getValue());
        }
        return new BlockModelDefinition(Optional.of(new BlockModelDefinition.Variants(models)), Optional.empty());
    }

    public static class Empty {
        private final Block block;

        Empty(Block block) {
            this.block = block;
        }

        public CustomVariantsBlockModelDefinitionCreator with(BlockStateVariantMap<BlockStateModel.Unbaked> map) {
            return CustomVariantsBlockModelDefinitionCreator.create(this.block, map);
        }

        public CustomVariantsBlockModelDefinitionCreator with(BlockStateModel.Unbaked model) {
            return CustomVariantsBlockModelDefinitionCreator.create(this.block, model);
        }

    }
}
