package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.predicate.LocationPredicateExtensions;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;
import java.util.function.Function;

@Mixin(LocationPredicate.class)
public abstract class LocationPredicateMixin implements LocationPredicateExtensions {

    @Shadow @Final @Mutable
    private Optional<LocationPredicate.PositionRange> position;

    @Shadow @Final @Mutable
    private Optional<RegistryEntryList<Biome>> biomes;

    @Override
    public void zine$setPosition(NumberRange.DoubleRange x, NumberRange.DoubleRange y, NumberRange.DoubleRange z) {
        this.position = LocationPredicate.PositionRange.create(x, y, z);
    }

    @Override
    public void zine$clearPosition() {
        this.position = Optional.empty();
    }

    @Override
    public void zine$setBiomes(@Nullable RegistryEntryList<Biome> biomes) {
        this.biomes = Optional.ofNullable(biomes);
    }

    @Override
    public void zine$addBiome(RegistryEntry<Biome> biome) {
        if(this.biomes.isPresent()) {
            this.biomes = Optional.of(ZineUtil.mergeValue(this.biomes.get(), Function.identity(), biome));
            return;
        }
        this.biomes = Optional.of(RegistryEntryList.of(biome));
    }

    @Override
    public void zine$addBiome(RegistryEntryLookup<Biome> biomeLookup, RegistryKey<Biome> biome) {
        if(this.biomes.isPresent()) {
            this.biomes = Optional.of(ZineUtil.mergeValue(this.biomes.get(), biomeLookup::getOrThrow, biome));
            return;
        }
        this.biomes = Optional.of(RegistryEntryList.of(biomeLookup::getOrThrow, biome));
    }
}
