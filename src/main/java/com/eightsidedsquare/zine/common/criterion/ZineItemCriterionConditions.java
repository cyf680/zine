package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.item.ItemPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineItemCriterionConditions {

    default void zine$setItem(@Nullable ItemPredicate item) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
