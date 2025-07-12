package com.eightsidedsquare.zine.mixin.client.item;

import com.eightsidedsquare.zine.client.item.ZineItemRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemRenderState.class)
public abstract class ItemRenderStateMixin implements ZineItemRenderState {

    @Shadow private ItemRenderState.LayerRenderState[] layers;

    @Shadow private int layerCount;

    @Override
    public ItemRenderState.LayerRenderState[] zine$getLayers() {
        return this.layers;
    }

    @Override
    public ItemRenderState.LayerRenderState zine$getLastLayer() {
        return this.layers[this.layerCount - 1];
    }
}
