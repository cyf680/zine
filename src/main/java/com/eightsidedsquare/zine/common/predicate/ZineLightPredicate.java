package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.predicate.NumberRange;

public interface ZineLightPredicate {

    default void zine$setRange(NumberRange.IntRange range) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
