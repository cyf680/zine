package com.eightsidedsquare.zine.client.data;

import net.minecraft.client.item.ItemAsset;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.item.Item;

public interface ZineItemModelOutput {

    default void zine$accept(Item item, ItemAsset itemAsset) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$accept(Item item, ItemModel.Unbaked model, ItemAsset.Properties properties) {
        this.zine$accept(item, new ItemAsset(model, properties));
    }

}
