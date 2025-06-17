package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineEntityCriterionConditions {

    default void zine$setEntity(@Nullable LootContextPredicate entity) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
