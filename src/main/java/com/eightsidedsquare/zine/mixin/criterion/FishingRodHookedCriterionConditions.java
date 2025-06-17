package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineFishingRodHookedCriterionConditions;
import net.minecraft.advancement.criterion.FishingRodHookedCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(FishingRodHookedCriterion.Conditions.class)
public abstract class FishingRodHookedCriterionConditions implements ZineFishingRodHookedCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<ItemPredicate> rod;

    @Override
    public void zine$setRod(@Nullable ItemPredicate rod) {
        this.rod = Optional.ofNullable(rod);
    }

}
