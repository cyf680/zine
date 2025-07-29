package com.eightsidedsquare.zine.common.text;

import net.minecraft.text.MutableText;

public interface ZineMutableText {

    default MutableText zine$freeze() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default MutableText zine$unfreeze() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
