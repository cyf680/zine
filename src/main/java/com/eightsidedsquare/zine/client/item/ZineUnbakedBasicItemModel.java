package com.eightsidedsquare.zine.client.item;

import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.util.Identifier;

import java.util.List;

public interface ZineUnbakedBasicItemModel {

    default void zine$addTint(TintSource tint) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addTints(List<TintSource> tints) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setTints(List<TintSource> tints) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setModel(Identifier model) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
