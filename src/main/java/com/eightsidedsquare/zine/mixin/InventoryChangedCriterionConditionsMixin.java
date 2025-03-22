package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.criterion.InventoryChangedCriterionConditionsExtensions;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Optional;

@Mixin(InventoryChangedCriterion.Conditions.class)
public abstract class InventoryChangedCriterionConditionsMixin implements InventoryChangedCriterionConditionsExtensions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Shadow @Final @Mutable
    private InventoryChangedCriterion.Conditions.Slots slots;

    @Shadow @Final @Mutable
    private List<ItemPredicate> items;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }

    @Override
    public void zine$setSlots(InventoryChangedCriterion.Conditions.Slots slots) {
        this.slots = slots;
    }

    @Override
    public void zine$setItems(List<ItemPredicate> items) {
        this.items = items;
    }

    @Override
    public void zine$addItem(ItemPredicate item) {
        this.items = ZineUtil.addOrUnfreeze(this.items, item);
    }

    @Override
    public void zine$addItems(List<ItemPredicate> items) {
        this.items = ZineUtil.addAllOrUnfreeze(this.items, items);
    }
}
