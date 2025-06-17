package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineUsedEnderEyeCriterionConditions {

    default void zine$setDistance(NumberRange.DoubleRange distance) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
