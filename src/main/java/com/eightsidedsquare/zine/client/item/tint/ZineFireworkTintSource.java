package com.eightsidedsquare.zine.client.item.tint;

public interface ZineFireworkTintSource {

    default void zine$setDefaultColor(int defaultColor) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
