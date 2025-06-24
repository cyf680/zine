package com.eightsidedsquare.zine.client.font;

public interface ZineOutlinable {

    default void zine$setOutlineColor(int outlineColor) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default int zine$getOutlineColor() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default boolean zine$hasOutline() {
        return this.zine$getOutlineColor() != 0;
    }

}
