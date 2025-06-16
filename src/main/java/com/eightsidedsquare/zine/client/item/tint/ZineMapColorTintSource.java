package com.eightsidedsquare.zine.client.item.tint;

public interface ZineMapColorTintSource {

    default void zine$setDefaultColor(int defaultColor) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
