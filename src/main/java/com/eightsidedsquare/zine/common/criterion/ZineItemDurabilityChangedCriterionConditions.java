package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.NumberRange;

public interface ZineItemDurabilityChangedCriterionConditions {

    default void zine$setDurability(NumberRange.IntRange durability) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setDelta(NumberRange.IntRange delta) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
