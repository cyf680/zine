package com.eightsidedsquare.zine.client.gui;

public interface ZineDrawContext {

    /**
     * Applies outlineColor to the next drawn text; must be called before each outlined text drawn
     * @param outlineColor color of the text outline in 0xAARRGGBB format
     */
    default void zine$prepareOutlineColor(int outlineColor) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
