package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.predicate.NumberRange;

public interface ZineDistancePredicate {

    default void zine$setX(NumberRange.DoubleRange x) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setY(NumberRange.DoubleRange y) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setZ(NumberRange.DoubleRange z) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setHorizontal(NumberRange.DoubleRange horizontal) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setAbsolute(NumberRange.DoubleRange absolute) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
