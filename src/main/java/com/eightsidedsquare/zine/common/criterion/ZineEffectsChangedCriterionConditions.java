package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.EntityEffectPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineEffectsChangedCriterionConditions {

    default void zine$setEffects(@Nullable EntityEffectPredicate effects) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setSource(@Nullable LootContextPredicate source) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
