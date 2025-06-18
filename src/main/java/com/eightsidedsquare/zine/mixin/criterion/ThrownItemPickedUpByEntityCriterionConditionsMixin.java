package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineEntityCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZineItemCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import net.minecraft.advancement.criterion.ThrownItemPickedUpByEntityCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(ThrownItemPickedUpByEntityCriterion.Conditions.class)
public abstract class ThrownItemPickedUpByEntityCriterionConditionsMixin implements ZinePlayerCriterionConditions,
        ZineItemCriterionConditions,
        ZineEntityCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Shadow @Final @Mutable
    private Optional<ItemPredicate> item;

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> entity;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }

    @Override
    public void zine$setItem(@Nullable ItemPredicate item) {
        this.item = Optional.ofNullable(item);
    }

    @Override
    public void zine$setEntity(@Nullable LootContextPredicate entity) {
        this.entity = Optional.ofNullable(entity);
    }
}
