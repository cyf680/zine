package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineLocationCriterionConditions {

    default void zine$setLocation(@Nullable LootContextPredicate location) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
