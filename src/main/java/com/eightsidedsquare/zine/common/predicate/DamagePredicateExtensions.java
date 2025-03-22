package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import org.jetbrains.annotations.Nullable;

public interface DamagePredicateExtensions {

    default void zine$setDealt(NumberRange.DoubleRange dealt) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setTaken(NumberRange.DoubleRange taken) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setSourceEntity(@Nullable EntityPredicate sourceEntity) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setBlocked(@Nullable Boolean blocked) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setType(@Nullable DamageSourcePredicate type) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
