package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.predicate.component.ComponentMapPredicate;
import net.minecraft.predicate.component.ComponentPredicate;

import java.util.Map;

public interface ZineComponentsPredicate {

    default void zine$setExact(ComponentMapPredicate exact) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setPartial(Map<ComponentPredicate.Type<?>, ComponentPredicate> partial) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addPartial(ComponentPredicate.Type<?> type, ComponentPredicate predicate) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
