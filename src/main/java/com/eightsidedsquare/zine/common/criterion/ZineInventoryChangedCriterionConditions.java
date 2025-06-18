package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.predicate.item.ItemPredicate;

import java.util.Collection;
import java.util.List;

public interface ZineInventoryChangedCriterionConditions {

    default void zine$setSlots(InventoryChangedCriterion.Conditions.Slots slots) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setItems(List<ItemPredicate> items) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addItem(ItemPredicate item) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addItems(Collection<ItemPredicate> items) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
