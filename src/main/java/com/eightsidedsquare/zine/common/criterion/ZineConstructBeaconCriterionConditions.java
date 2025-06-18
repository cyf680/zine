package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.NumberRange;

public interface ZineConstructBeaconCriterionConditions {

    default void zine$setLevel(NumberRange.IntRange level) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
