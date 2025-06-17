package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineOnKilledCriterionConditions {

    default void zine$setKillingBlow(@Nullable DamageSourcePredicate killingBlow) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
