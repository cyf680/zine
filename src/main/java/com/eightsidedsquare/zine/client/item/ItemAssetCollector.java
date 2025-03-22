package com.eightsidedsquare.zine.client.item;

import net.minecraft.client.item.ItemAsset;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface ItemAssetCollector extends BiConsumer<Identifier, ItemAsset> {

    default void accept(Identifier id, ItemModel.Unbaked unbaked) {
        this.accept(id, new ItemAsset(unbaked, ItemAsset.Properties.DEFAULT));
    }

    default void accept(ItemConvertible item, ItemAsset itemAsset) {
        this.accept(Registries.ITEM.getId(item.asItem()), itemAsset);
    }

    default void accept(ItemConvertible item, ItemModel.Unbaked unbaked) {
        this.accept(Registries.ITEM.getId(item.asItem()), unbaked);
    }

}