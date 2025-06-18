package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.block.Block;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.entry.RegistryEntryList;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface ZineBlockPredicate {

    default void zine$setBlocks(@Nullable RegistryEntryList<Block> blocks) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addBlock(Block block) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addBlocks(Collection<Block> blocks) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setState(@Nullable StatePredicate state) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setNbt(@Nullable NbtPredicate nbt) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
