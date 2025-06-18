package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.FluidPredicate;
import net.minecraft.predicate.LightPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.Structure;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface ZineLocationPredicate {

    default void zine$setPosition(NumberRange.DoubleRange x, NumberRange.DoubleRange y, NumberRange.DoubleRange z) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$clearPosition() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setBiomes(@Nullable RegistryEntryList<Biome> biomes) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addBiome(RegistryEntry<Biome> biome) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addBiome(RegistryEntryLookup<Biome> biomeLookup, RegistryKey<Biome> biome) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addBiomes(RegistryEntryList<Biome> biomes) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addBiomes(RegistryEntryLookup<Biome> biomeLookup, Collection<RegistryKey<Biome>> biomes) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setStructures(@Nullable RegistryEntryList<Structure> structures) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addStructure(RegistryEntry<Structure> structure) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addStructure(RegistryEntryLookup<Structure> structureLookup, RegistryKey<Structure> structure) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addStructures(RegistryEntryList<Structure> structures) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addStructures(RegistryEntryLookup<Structure> structureLookup, Collection<RegistryKey<Structure>> structures) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setDimension(@Nullable RegistryKey<World> dimension) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setSmokey(@Nullable Boolean smokey) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setLight(@Nullable LightPredicate light) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setBlock(@Nullable BlockPredicate block) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setFluid(@Nullable FluidPredicate fluid) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setCanSeeSky(@Nullable Boolean canSeeSky) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
