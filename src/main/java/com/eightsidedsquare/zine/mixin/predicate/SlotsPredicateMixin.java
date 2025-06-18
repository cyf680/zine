package com.eightsidedsquare.zine.mixin.predicate;

import com.eightsidedsquare.zine.common.predicate.ZineSlotsPredicate;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.inventory.SlotRange;
import net.minecraft.predicate.entity.SlotsPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(SlotsPredicate.class)
public abstract class SlotsPredicateMixin implements ZineSlotsPredicate {

    @Shadow @Final @Mutable
    private Map<SlotRange, ItemPredicate> slots;

    @Override
    public void zine$setSlots(Map<SlotRange, ItemPredicate> slots) {
        this.slots = slots;
    }

    @Override
    public void zine$addSlot(SlotRange slotRange, ItemPredicate itemPredicate) {
        this.slots = ZineUtil.putOrUnfreeze(this.slots, slotRange, itemPredicate);
    }
}
