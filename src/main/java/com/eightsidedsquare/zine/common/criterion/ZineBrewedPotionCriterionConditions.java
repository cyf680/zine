package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.potion.Potion;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;

public interface ZineBrewedPotionCriterionConditions {

    default void zine$setPotion(@Nullable RegistryEntry<Potion> potion) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setPotion(@Nullable Potion potion) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
