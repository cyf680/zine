package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineItemCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZineItemDurabilityChangedCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import net.minecraft.advancement.criterion.ItemDurabilityChangedCriterion;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(ItemDurabilityChangedCriterion.Conditions.class)
public abstract class ItemDurabilityChangedCriterionConditionsMixin implements ZinePlayerCriterionConditions,
        ZineItemCriterionConditions,
        ZineItemDurabilityChangedCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Shadow @Final @Mutable
    private Optional<ItemPredicate> item;

    @Shadow @Final @Mutable
    private NumberRange.IntRange durability;

    @Shadow @Final @Mutable
    private NumberRange.IntRange delta;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }

    @Override
    public void zine$setItem(@Nullable ItemPredicate item) {
        this.item = Optional.ofNullable(item);
    }

    @Override
    public void zine$setDurability(NumberRange.IntRange durability) {
        this.durability = durability;
    }

    @Override
    public void zine$setDelta(NumberRange.IntRange delta) {
        this.delta = delta;
    }
}
