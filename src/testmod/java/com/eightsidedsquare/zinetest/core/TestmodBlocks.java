package com.eightsidedsquare.zinetest.core;

import com.eightsidedsquare.zinetest.common.block.SillyShapeBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public interface TestmodBlocks {

    Block TOURMALINE_BLOCK = TestmodInit.REGISTRY.blockWithItem("tourmaline_block", AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK));
    Block GOO = TestmodInit.REGISTRY.blockWithItem("goo", AbstractBlock.Settings.copy(Blocks.SLIME_BLOCK));
    Block SILLY_SHAPE = TestmodInit.REGISTRY.blockWithItem("silly_shape", AbstractBlock.Settings.copy(Blocks.BIG_DRIPLEAF), SillyShapeBlock::new);
    Block WOOD = TestmodInit.REGISTRY.blockWithItem("wood", AbstractBlock.Settings.copy(Blocks.PALE_OAK_PLANKS));
    Block RAINBOW = TestmodInit.REGISTRY.blockWithItem("rainbow", AbstractBlock.Settings.copy(Blocks.RED_CONCRETE));

    static void init() {
    }

}
