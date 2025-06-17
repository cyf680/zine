package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineFishingRodHookedCriterionConditions {

    default void zine$setRod(@Nullable ItemPredicate rod) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }
}
