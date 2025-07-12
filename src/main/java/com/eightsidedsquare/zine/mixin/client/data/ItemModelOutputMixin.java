package com.eightsidedsquare.zine.mixin.client.data;

import com.eightsidedsquare.zine.client.data.ZineItemModelOutput;
import net.minecraft.client.data.ItemModelOutput;
import net.minecraft.client.item.ItemAsset;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemModelOutput.class)
public interface ItemModelOutputMixin extends ZineItemModelOutput {

    @Shadow void accept(Item item, ItemModel.Unbaked model);

    @Override
    default void zine$accept(Item item, ItemAsset itemAsset) {
        this.accept(item, itemAsset.model());
    }
}
