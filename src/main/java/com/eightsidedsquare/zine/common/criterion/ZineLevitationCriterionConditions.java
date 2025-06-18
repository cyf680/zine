package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.NumberRange;

public interface ZineLevitationCriterionConditions {

    default void zine$setDuration(NumberRange.IntRange duration) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
