package com.eightsidedsquare.zine.client.item;

import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.RangeDispatchItemModel;
import net.minecraft.client.render.item.property.numeric.NumericProperty;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ZineUnbakedRangeDispatchItemModel {

    default void zine$setProperty(NumericProperty property) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setScale(float scale) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setFallback(@Nullable ItemModel.Unbaked fallback) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addEntry(RangeDispatchItemModel.Entry entry) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addEntries(List<RangeDispatchItemModel.Entry> entries) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
