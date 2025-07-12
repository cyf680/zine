package com.eightsidedsquare.zine.mixin.advancement.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineBeeNestDestroyedCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZineBlockCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZineItemCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import net.minecraft.advancement.criterion.BeeNestDestroyedCriterion;
import net.minecraft.block.Block;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(BeeNestDestroyedCriterion.Conditions.class)
public abstract class BeeNestDestroyedCriterionConditionsMixin implements
        ZinePlayerCriterionConditions,
        ZineBlockCriterionConditions,
        ZineItemCriterionConditions,
        ZineBeeNestDestroyedCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Shadow @Final @Mutable
    private Optional<RegistryEntry<Block>> block;

    @Shadow @Final @Mutable
    private Optional<ItemPredicate> item;

    @Shadow @Final @Mutable
    private NumberRange.IntRange beesInside;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }

    @Override
    public void zine$setBlock(@Nullable RegistryEntry<Block> block) {
        this.block = Optional.ofNullable(block);
    }

    @Override
    public void zine$setItem(@Nullable ItemPredicate item) {
        this.item = Optional.ofNullable(item);
    }

    @Override
    public void zine$setBeesInside(NumberRange.IntRange beesInside) {
        this.beesInside = beesInside;
    }
}
