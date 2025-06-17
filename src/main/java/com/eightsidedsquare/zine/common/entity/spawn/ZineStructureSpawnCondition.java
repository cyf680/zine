package com.eightsidedsquare.zine.common.entity.spawn;

import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.gen.structure.Structure;

import java.util.Collection;

public interface ZineStructureSpawnCondition {

    default void zine$setRequiredStructures(RegistryEntryList<Structure> requiredStructures) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addRequiredStructure(RegistryEntry<Structure> requiredStructure) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addRequiredStructure(RegistryEntryLookup<Structure> structureLookup, RegistryKey<Structure> requiredStructure) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addRequiredStructures(RegistryEntryList<Structure> requiredStructures) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addRequiredStructures(RegistryEntryLookup<Structure> structureLookup, Collection<RegistryKey<Structure>> requiredStructures) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
