package com.eightsidedsquare.zinetest.core;

import net.minecraft.block.*;

public interface TestmodBlocks {

    Block TOURMALINE_BLOCK = TestmodInit.REGISTRY.blockWithItem("tourmaline_block", AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK));
    Block GOO = TestmodInit.REGISTRY.blockWithItem("goo", AbstractBlock.Settings.copy(Blocks.SLIME_BLOCK));
    Block WOOD = TestmodInit.REGISTRY.blockWithItem("wood", AbstractBlock.Settings.copy(Blocks.PALE_OAK_PLANKS));
    Block RAINBOW = TestmodInit.REGISTRY.blockWithItem("rainbow", AbstractBlock.Settings.copy(Blocks.RED_CONCRETE));
    Block BIG_DIAMOND = TestmodInit.REGISTRY.blockWithItem("big_diamond", AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK));

    static void init() {
    }

}
