package com.eightsidedsquare.zine.common.world.structure;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;

public interface FeaturePoolElementExtensions {

    default RegistryEntry<PlacedFeature> zine$getFeature() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setFeature(RegistryEntry<PlacedFeature> feature) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default NbtCompound zine$getNbt() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setNbt(NbtCompound nbt) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
