package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.nbt.NbtCompound;

public interface ZineNbtPredicate {

    default void zine$setNbt(NbtCompound nbt) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
