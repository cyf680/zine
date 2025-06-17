package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.DistancePredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineLevitationCriterionConditions {

    default void zine$setDuration(NumberRange.IntRange duration) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
