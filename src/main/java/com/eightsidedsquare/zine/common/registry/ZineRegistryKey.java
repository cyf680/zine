package com.eightsidedsquare.zine.common.registry;

import net.minecraft.text.MutableText;

public interface ZineRegistryKey<T> {

    default String zine$getTranslationKey() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default MutableText zine$getName() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
