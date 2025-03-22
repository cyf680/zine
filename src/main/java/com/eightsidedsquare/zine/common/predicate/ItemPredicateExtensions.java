package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.item.Item;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.component.ComponentsPredicate;
import net.minecraft.registry.entry.RegistryEntryList;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface ItemPredicateExtensions {

    default void zine$setItems(@Nullable RegistryEntryList<Item> items) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addItem(Item item) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addItems(Collection<Item> items) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setCount(NumberRange.IntRange count) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setComponents(ComponentsPredicate components) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
