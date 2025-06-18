package com.eightsidedsquare.zine.common.entity.spawn;

import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.biome.Biome;

import java.util.Collection;

public interface ZineBiomeSpawnCondition {

    default void zine$setRequiredBiomes(RegistryEntryList<Biome> requiredBiomes) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addRequiredBiome(RegistryEntry<Biome> requiredBiome) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addRequiredBiome(RegistryEntryLookup<Biome> biomeLookup, RegistryKey<Biome> requiredBiome) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addRequiredBiomes(RegistryEntryList<Biome> requiredBiomes) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addRequiredBiomes(RegistryEntryLookup<Biome> biomeLookup, Collection<RegistryKey<Biome>> requiredBiomes) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
