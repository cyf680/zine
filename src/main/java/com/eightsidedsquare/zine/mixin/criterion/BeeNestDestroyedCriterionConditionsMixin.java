package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineBeeNestDestroyedCriterionConditions;
import net.minecraft.advancement.criterion.BeeNestDestroyedCriterion;
import net.minecraft.block.Block;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(BeeNestDestroyedCriterion.Conditions.class)
public abstract class BeeNestDestroyedCriterionConditionsMixin implements ZineBeeNestDestroyedCriterionConditions {

    @Shadow @Final @Mutable
    private NumberRange.IntRange beesInside;

    @Override
    public void zine$setBeesInside(NumberRange.IntRange beesInside) {
        this.beesInside = beesInside;
    }
}
