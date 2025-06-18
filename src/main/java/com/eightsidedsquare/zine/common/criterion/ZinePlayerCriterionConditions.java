package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZinePlayerCriterionConditions {

    default void zine$setPlayer(@Nullable LootContextPredicate player) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
