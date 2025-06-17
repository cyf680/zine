package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineTargetHitCriterionConditions {

    default void zine$setSignalStrength(NumberRange.IntRange signalStrength) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setProjectile(@Nullable LootContextPredicate projectile) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
