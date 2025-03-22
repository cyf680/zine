package com.eightsidedsquare.zine.client.item;

import net.minecraft.client.render.item.model.ItemModel;

import java.util.List;

public interface UnbakedCompositeItemModelExtensions {

    default void zine$addModel(ItemModel.Unbaked model) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addModels(List<ItemModel.Unbaked> models) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setModels(List<ItemModel.Unbaked> models) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
