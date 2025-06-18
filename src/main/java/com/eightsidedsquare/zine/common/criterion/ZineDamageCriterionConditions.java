package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.DamagePredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineDamageCriterionConditions {

    default void zine$setDamage(@Nullable DamagePredicate damage) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
