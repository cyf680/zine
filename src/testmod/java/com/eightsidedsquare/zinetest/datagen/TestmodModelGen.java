package com.eightsidedsquare.zinetest.datagen;

import com.eightsidedsquare.zine.client.block.BlockStateModels;
import com.eightsidedsquare.zine.client.data.BlockModelDefinitions;
import com.eightsidedsquare.zinetest.core.TestmodBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.ModelIds;

public class TestmodModelGen extends FabricModelProvider {

    @SuppressWarnings("UnstableApiUsage")
    public TestmodModelGen(FabricDataOutput output) {
        super(new FabricDataOutput(output.getModContainer(), output.getPath(), false));
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.blockStateCollector.accept(
                BlockModelDefinitions.customVariants(
                        TestmodBlocks.WOOD,
                        BlockStateModels.connected(
                                ModelIds.getBlockModelId(TestmodBlocks.WOOD),
                                true
                        )
                )
        );
        generator.blockStateCollector.accept(
                BlockModelDefinitions.customVariants(
                        TestmodBlocks.RAINBOW,
                        BlockStateModels.tessellating(
                                ModelIds.getBlockModelId(TestmodBlocks.RAINBOW),
                                4
                        )
                )
        );
        generator.blockStateCollector.accept(
                BlockModelDefinitions.customVariants(
                        TestmodBlocks.BIG_DIAMOND,
                        BlockStateModels.tessellating(
                                ModelIds.getBlockModelId(TestmodBlocks.BIG_DIAMOND),
                                7
                        )
                )
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
    }
}
