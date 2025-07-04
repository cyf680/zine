package com.eightsidedsquare.zine.client.data;

import net.minecraft.block.Block;
import net.minecraft.client.data.BlockModelDefinitionCreator;
import net.minecraft.client.render.model.json.BlockModelDefinition;

import java.util.Optional;

public class DirectBlockModelDefinitionCreator implements BlockModelDefinitionCreator {

    private final Block block;
    private final BlockModelDefinition definition;

    private DirectBlockModelDefinitionCreator(Block block, BlockModelDefinition definition) {
        this.block = block;
        this.definition = definition;
    }

    public static DirectBlockModelDefinitionCreator create(Block block, BlockModelDefinition definition) {
        return new DirectBlockModelDefinitionCreator(block, definition);
    }

    public static DirectBlockModelDefinitionCreator create(Block block, BlockModelDefinition.Variants variants) {
        return create(block, new BlockModelDefinition(Optional.of(variants), Optional.empty()));
    }

    public static DirectBlockModelDefinitionCreator create(Block block, BlockModelDefinition.Multipart multipart) {
        return create(block, new BlockModelDefinition(Optional.empty(), Optional.of(multipart)));
    }

    @Override
    public Block getBlock() {
        return this.block;
    }

    @Override
    public BlockModelDefinition createBlockModelDefinition() {
        return this.definition;
    }
}
