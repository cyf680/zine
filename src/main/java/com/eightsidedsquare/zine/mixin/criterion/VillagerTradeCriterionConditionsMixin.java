package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineItemCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZineVillagerCriterionConditions;
import net.minecraft.advancement.criterion.UsingItemCriterion;
import net.minecraft.advancement.criterion.VillagerTradeCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(VillagerTradeCriterion.Conditions.class)
public abstract class VillagerTradeCriterionConditionsMixin implements ZinePlayerCriterionConditions,
        ZineVillagerCriterionConditions,
        ZineItemCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> villager;

    @Shadow @Final @Mutable
    private Optional<ItemPredicate> item;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }

    @Override
    public void zine$setVillager(@Nullable LootContextPredicate villager) {
        this.villager = Optional.ofNullable(villager);
    }

    @Override
    public void zine$setItem(@Nullable ItemPredicate item) {
        this.item = Optional.ofNullable(item);
    }
}
