package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineConstructBeaconCriterionConditions {

    default void zine$setLevel(NumberRange.IntRange level) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
