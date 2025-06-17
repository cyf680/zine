package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineBrewedPotionCriterionConditions;
import net.minecraft.advancement.criterion.BrewedPotionCriterion;
import net.minecraft.potion.Potion;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(BrewedPotionCriterion.Conditions.class)
public abstract class BrewedPotionCriterionConditionsMixin implements ZineBrewedPotionCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<RegistryEntry<Potion>> potion;

    @Override
    public void zine$setPotion(@Nullable RegistryEntry<Potion> potion) {
        this.potion = Optional.ofNullable(potion);
    }

    @Override
    public void zine$setPotion(@Nullable Potion potion) {
        this.potion = Optional.ofNullable(potion == null ? null : Registries.POTION.getEntry(potion));
    }
}
