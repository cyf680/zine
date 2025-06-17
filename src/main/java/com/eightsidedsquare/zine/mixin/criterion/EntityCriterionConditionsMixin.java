package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineEntityCriterionConditions;
import net.minecraft.advancement.criterion.*;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(value = {
        FishingRodHookedCriterion.Conditions.class,
        OnKilledCriterion.Conditions.class,
        PlayerHurtEntityCriterion.Conditions.class,
        PlayerInteractedWithEntityCriterion.Conditions.class,
        SummonedEntityCriterion.Conditions.class,
        TameAnimalCriterion.Conditions.class,
        ThrownItemPickedUpByEntityCriterion.Conditions.class
})
public abstract class EntityCriterionConditionsMixin implements ZineEntityCriterionConditions {

    @Shadow(remap = false) @Final @Mutable
    private Optional<LootContextPredicate> entity;

    @Override
    public void zine$setEntity(@Nullable LootContextPredicate entity) {
        this.entity = Optional.ofNullable(entity);
    }
}
