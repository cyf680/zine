package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineEnchantedItemCriterionConditions;
import net.minecraft.advancement.criterion.EnchantedItemCriterion;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(EnchantedItemCriterion.Conditions.class)
public abstract class EnchantedItemCriterionConditionsMixin implements ZineEnchantedItemCriterionConditions {

    @Shadow @Final @Mutable
    private NumberRange.IntRange levels;

    @Override
    public void zine$setLevels(NumberRange.IntRange levels) {
        this.levels = levels;
    }
}
