package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.block.Block;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;

public interface ZineBlockCriterionConditions {

    default void zine$setBlock(@Nullable RegistryEntry<Block> block) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setBlock(@Nullable Block block) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
