package com.eightsidedsquare.zine.client.item;

import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.SelectItemModel;
import net.minecraft.client.render.item.property.select.SelectProperty;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ZineUnbakedSelectItemModel {

    default void zine$setFallback(@Nullable ItemModel.Unbaked fallback) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setSwitch(SelectItemModel.UnbakedSwitch<?, ?> unbakedSwitch) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default <P extends SelectProperty<T>, T> void zine$addCase(SelectProperty.Type<P, T> type, List<T> values, ItemModel.Unbaked model) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default <P extends SelectProperty<T>, T> void zine$addCases(SelectProperty.Type<P, T> type, List<SelectItemModel.SwitchCase<T>> cases) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
