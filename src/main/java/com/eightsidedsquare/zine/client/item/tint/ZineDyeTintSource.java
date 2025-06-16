package com.eightsidedsquare.zine.client.item.tint;

public interface ZineDyeTintSource {

    default void zine$setDefaultColor(int defaultColor) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
