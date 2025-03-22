package com.eightsidedsquare.zine.client.item;

import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.util.Identifier;

public interface UnbakedSpecialItemModelExtensions {

    default void zine$setBase(Identifier base) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setModel(SpecialModelRenderer.Unbaked model) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
