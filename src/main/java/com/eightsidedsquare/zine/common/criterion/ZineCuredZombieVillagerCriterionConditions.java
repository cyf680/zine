package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineCuredZombieVillagerCriterionConditions {

    default void zine$setZombie(@Nullable LootContextPredicate zombie) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
