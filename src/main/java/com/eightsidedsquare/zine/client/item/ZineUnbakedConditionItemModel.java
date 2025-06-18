package com.eightsidedsquare.zine.client.item;

import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.property.bool.BooleanProperty;

public interface ZineUnbakedConditionItemModel {

    default void zine$setProperty(BooleanProperty property) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setTrueModel(ItemModel.Unbaked model) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setFalseModel(ItemModel.Unbaked model) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
