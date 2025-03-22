package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.component.Component;
import net.minecraft.component.ComponentType;

import java.util.List;

public interface ComponentMapPredicateExtensions {

    default void zine$setComponents(List<Component<?>> components) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addComponent(Component<?> component) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default <T> void zine$addComponent(ComponentType<T> type, T value) {
        this.zine$addComponent(new Component<>(type, value));
    }

    default void zine$addComponents(List<Component<?>> components) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
