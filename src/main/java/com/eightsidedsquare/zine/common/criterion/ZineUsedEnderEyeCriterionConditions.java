package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.NumberRange;

public interface ZineUsedEnderEyeCriterionConditions {

    default void zine$setDistance(NumberRange.DoubleRange distance) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
