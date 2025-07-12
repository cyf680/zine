package com.eightsidedsquare.zine.mixin.client.data;

import net.minecraft.client.data.ItemModelOutput;
import net.minecraft.client.item.ItemAsset;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "net/minecraft/client/data/ModelProvider$ItemAssets")
public abstract class ModelProviderItemAssetsMixin implements ItemModelOutput {

    @Shadow protected abstract void accept(Item item, ItemAsset asset);

    @Override
    public void zine$accept(Item item, ItemAsset itemAsset) {
        this.accept(item, itemAsset);
    }
}
