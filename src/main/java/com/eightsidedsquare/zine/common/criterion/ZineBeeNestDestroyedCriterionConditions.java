package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.NumberRange;

public interface ZineBeeNestDestroyedCriterionConditions {

    default void zine$setBeesInside(NumberRange.IntRange beesInside) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
