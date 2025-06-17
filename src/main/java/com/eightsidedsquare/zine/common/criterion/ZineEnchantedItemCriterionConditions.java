package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineEnchantedItemCriterionConditions {

    default void zine$setLevels(NumberRange.IntRange levels) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
