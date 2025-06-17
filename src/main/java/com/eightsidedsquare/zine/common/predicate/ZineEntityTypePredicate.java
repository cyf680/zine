package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.entity.EntityType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;

import java.util.Collection;

public interface ZineEntityTypePredicate {

    default void zine$setTypes(RegistryEntryList<EntityType<?>> types) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addType(EntityType<?> type) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addType(RegistryEntry<EntityType<?>> type) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addTypes(RegistryEntryList<EntityType<?>> types) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addTypes(Collection<EntityType<?>> types) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
