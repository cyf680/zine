package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.predicate.NumberRange;

public interface LightPredicateExtensions {

    default void zine$setRange(NumberRange.IntRange range) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
