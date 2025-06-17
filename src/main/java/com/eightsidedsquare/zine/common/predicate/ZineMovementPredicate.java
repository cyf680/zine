package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.predicate.NumberRange;

public interface ZineMovementPredicate {

    default void zine$setX(NumberRange.DoubleRange x) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setY(NumberRange.DoubleRange y) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setZ(NumberRange.DoubleRange z) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setSpeed(NumberRange.DoubleRange speed) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setHorizontalSpeed(NumberRange.DoubleRange horizontalSpeed) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setVerticalSpeed(NumberRange.DoubleRange verticalSpeed) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setFallDistance(NumberRange.DoubleRange fallDistance) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
