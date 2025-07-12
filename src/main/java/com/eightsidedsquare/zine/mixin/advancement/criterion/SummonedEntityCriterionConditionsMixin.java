package com.eightsidedsquare.zine.mixin.advancement.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineEntityCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import net.minecraft.advancement.criterion.SummonedEntityCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(SummonedEntityCriterion.Conditions.class)
public abstract class SummonedEntityCriterionConditionsMixin implements ZinePlayerCriterionConditions,
        ZineEntityCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> entity;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }

    @Override
    public void zine$setEntity(@Nullable LootContextPredicate entity) {
        this.entity = Optional.ofNullable(entity);
    }
}
