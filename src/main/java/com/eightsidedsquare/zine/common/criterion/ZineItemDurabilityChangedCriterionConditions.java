package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineItemDurabilityChangedCriterionConditions {

    default void zine$setDurability(NumberRange.IntRange durability) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setDelta(NumberRange.IntRange delta) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
