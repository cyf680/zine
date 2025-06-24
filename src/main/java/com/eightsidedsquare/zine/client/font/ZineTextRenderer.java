package com.eightsidedsquare.zine.client.font;

import org.jetbrains.annotations.ApiStatus;

public interface ZineTextRenderer {

    @ApiStatus.Internal
    default void zine$prepareOutlineColor(int outlineColor) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
