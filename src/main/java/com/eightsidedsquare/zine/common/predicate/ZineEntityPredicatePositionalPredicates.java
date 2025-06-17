package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.predicate.entity.LocationPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineEntityPredicatePositionalPredicates {

    default void zine$setLocated(@Nullable LocationPredicate located) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setSteppingOn(@Nullable LocationPredicate steppingOn) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setAffectsMovement(@Nullable LocationPredicate affectsMovement) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
