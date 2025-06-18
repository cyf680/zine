package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.StatePredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineStateCriterionConditions {

    default void zine$setState(@Nullable StatePredicate state) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
