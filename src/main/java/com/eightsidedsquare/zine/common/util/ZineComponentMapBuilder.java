package com.eightsidedsquare.zine.common.util;

import net.minecraft.component.Component;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import org.jetbrains.annotations.Nullable;

public interface ZineComponentMapBuilder {

    @Nullable
    default <T> T zine$get(ComponentType<T> type) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default <T> ComponentMap.Builder zine$add(Component<T> component) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default <T> ComponentMap.Builder zine$remove(ComponentType<T> type) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
