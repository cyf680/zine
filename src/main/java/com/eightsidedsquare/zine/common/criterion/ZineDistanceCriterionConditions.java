package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.DistancePredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineDistanceCriterionConditions {

    default void zine$setDistance(@Nullable DistancePredicate distance) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
