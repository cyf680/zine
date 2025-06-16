package com.eightsidedsquare.zine.mixin.predicate;

import com.eightsidedsquare.zine.common.predicate.ZineItemPredicate;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.item.Item;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.component.ComponentsPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntryList;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.Optional;

@Mixin(ItemPredicate.class)
public abstract class ItemPredicateMixin implements ZineItemPredicate {

    @Shadow @Final @Mutable
    private Optional<RegistryEntryList<Item>> items;

    @Shadow @Final @Mutable
    private NumberRange.IntRange count;

    @Shadow @Final @Mutable
    private ComponentsPredicate components;

    @Override
    public void zine$setItems(@Nullable RegistryEntryList<Item> items) {
        this.items = Optional.ofNullable(items);
    }

    @Override
    public void zine$addItem(Item item) {
        if(this.items.isPresent()) {
            this.items = Optional.of(ZineUtil.mergeValue(this.items.get(), Registries.ITEM::getEntry, item));
            return;
        }
        this.items = Optional.of(RegistryEntryList.of(Registries.ITEM::getEntry, item));
    }

    @Override
    public void zine$addItems(Collection<Item> items) {
        if(this.items.isPresent()) {
            this.items = Optional.of(ZineUtil.mergeValues(this.items.get(), Registries.ITEM::getEntry, items));
            return;
        }
        this.items = Optional.of(RegistryEntryList.of(Registries.ITEM::getEntry, items));
    }

    @Override
    public void zine$setCount(NumberRange.IntRange count) {
        this.count = count;
    }

    @Override
    public void zine$setComponents(ComponentsPredicate components) {
        this.components = components;
    }
}
