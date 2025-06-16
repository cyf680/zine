package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.world.structure.ZineFeaturePoolElement;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.FeaturePoolElement;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FeaturePoolElement.class)
public abstract class FeaturePoolElementMixin implements ZineFeaturePoolElement {

    @Shadow @Final @Mutable
    private RegistryEntry<PlacedFeature> feature;

    @Shadow @Final @Mutable
    private NbtCompound nbt;

    @Override
    public RegistryEntry<PlacedFeature> zine$getFeature() {
        return this.feature;
    }

    @Override
    public void zine$setFeature(RegistryEntry<PlacedFeature> feature) {
        this.feature = feature;
    }

    @Override
    public NbtCompound zine$getNbt() {
        return this.nbt;
    }

    @Override
    public void zine$setNbt(NbtCompound nbt) {
        this.nbt = nbt;
    }
}
