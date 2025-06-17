package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.inventory.SlotRange;
import net.minecraft.predicate.item.ItemPredicate;

import java.util.Map;

public interface ZineSlotsPredicate {

    default void zine$setSlots(Map<SlotRange, ItemPredicate> slots) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addSlot(SlotRange slotRange, ItemPredicate itemPredicate) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
