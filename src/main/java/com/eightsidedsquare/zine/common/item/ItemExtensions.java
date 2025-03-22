package com.eightsidedsquare.zine.common.item;

import net.minecraft.util.Identifier;

public interface ItemExtensions {

    default boolean zine$modelEquals(Identifier modelId) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
