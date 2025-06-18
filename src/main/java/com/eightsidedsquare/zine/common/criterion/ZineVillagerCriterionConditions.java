package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineVillagerCriterionConditions {

    default void zine$setVillager(@Nullable LootContextPredicate villager) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
