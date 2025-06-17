package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineBredAnimalsCriterionConditions {

    default void zine$setParent(@Nullable LootContextPredicate parent) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setPartner(@Nullable LootContextPredicate partner) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setChild(@Nullable LootContextPredicate child) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
