package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineItemCriterionConditions;
import net.minecraft.advancement.criterion.*;
import net.minecraft.predicate.item.ItemPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(value = {
        BeeNestDestroyedCriterion.Conditions.class,
        ConsumeItemCriterion.Conditions.class,
        EnchantedItemCriterion.Conditions.class,
        FilledBucketCriterion.Conditions.class,
        FishingRodHookedCriterion.Conditions.class,
        ItemDurabilityChangedCriterion.Conditions.class,
        PlayerInteractedWithEntityCriterion.Conditions.class,
        ShotCrossbowCriterion.Conditions.class,
        ThrownItemPickedUpByEntityCriterion.Conditions.class,
        UsedTotemCriterion.Conditions.class,
        UsingItemCriterion.Conditions.class,
        VillagerTradeCriterion.Conditions.class
})
public abstract class ItemCriterionConditionsMixin implements ZineItemCriterionConditions {

    @Shadow(remap = false) @Final @Mutable
    private Optional<ItemPredicate> item;

    @Override
    public void zine$setItem(@Nullable ItemPredicate item) {
        this.item = Optional.ofNullable(item);
    }
}
