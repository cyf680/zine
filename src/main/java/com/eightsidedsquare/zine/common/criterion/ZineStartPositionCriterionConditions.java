package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.LocationPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineStartPositionCriterionConditions {

    default void zine$setStartPosition(@Nullable LocationPredicate startPosition) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
