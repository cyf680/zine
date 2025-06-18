package com.eightsidedsquare.zine.mixin.predicate;

import com.eightsidedsquare.zine.common.predicate.ZineLocationPredicate;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.FluidPredicate;
import net.minecraft.predicate.LightPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.Structure;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

@Mixin(LocationPredicate.class)
public abstract class LocationPredicateMixin implements ZineLocationPredicate {

    @Shadow @Final @Mutable
    private Optional<LocationPredicate.PositionRange> position;

    @Shadow @Final @Mutable
    private Optional<RegistryEntryList<Biome>> biomes;

    @Shadow @Final @Mutable
    private Optional<RegistryEntryList<Structure>> structures;

    @Shadow @Final @Mutable
    private Optional<RegistryKey<World>> dimension;

    @Shadow @Final @Mutable
    private Optional<Boolean> smokey;

    @Shadow @Final @Mutable
    private Optional<LightPredicate> light;

    @Shadow @Final @Mutable
    private Optional<BlockPredicate> block;

    @Shadow @Final @Mutable
    private Optional<FluidPredicate> fluid;

    @Shadow @Final @Mutable
    private Optional<Boolean> canSeeSky;

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

    @Override
    public void zine$addBiomes(RegistryEntryList<Biome> biomes) {
        if(this.biomes.isPresent()) {
            this.biomes = Optional.of(ZineUtil.mergeValues(this.biomes.get(), biomes));
            return;
        }
        this.biomes = Optional.of(biomes);
    }

    @Override
    public void zine$addBiomes(RegistryEntryLookup<Biome> biomeLookup, Collection<RegistryKey<Biome>> biomes) {
        if(this.biomes.isPresent()) {
            this.biomes = Optional.of(ZineUtil.mergeValues(this.biomes.get(), biomeLookup::getOrThrow, biomes));
            return;
        }
        this.biomes = Optional.of(RegistryEntryList.of(biomeLookup::getOrThrow, biomes));
    }

    @Override
    public void zine$setStructures(@Nullable RegistryEntryList<Structure> structures) {
        this.structures = Optional.ofNullable(structures);
    }

    @Override
    public void zine$addStructure(RegistryEntry<Structure> structure) {
        if(this.structures.isPresent()) {
            this.structures = Optional.of(ZineUtil.mergeValue(this.structures.get(), Function.identity(), structure));
            return;
        }
        this.structures = Optional.of(RegistryEntryList.of(structure));
    }

    @Override
    public void zine$addStructure(RegistryEntryLookup<Structure> structureLookup, RegistryKey<Structure> structure) {
        if(this.structures.isPresent()) {
            this.structures = Optional.of(ZineUtil.mergeValue(this.structures.get(), structureLookup::getOrThrow, structure));
            return;
        }
        this.structures = Optional.of(RegistryEntryList.of(structureLookup::getOrThrow, structure));
    }

    @Override
    public void zine$addStructures(RegistryEntryLookup<Structure> structureLookup, Collection<RegistryKey<Structure>> structures) {
        if(this.structures.isPresent()) {
            this.structures = Optional.of(ZineUtil.mergeValues(this.structures.get(), structureLookup::getOrThrow, structures));
            return;
        }
        this.structures = Optional.of(RegistryEntryList.of(structureLookup::getOrThrow, structures));
    }

    @Override
    public void zine$addStructures(RegistryEntryList<Structure> structures) {
        if(this.structures.isPresent()) {
            this.structures = Optional.of(ZineUtil.mergeValues(this.structures.get(), structures));
            return;
        }
        this.structures = Optional.of(structures);
    }

    @Override
    public void zine$setDimension(@Nullable RegistryKey<World> dimension) {
        this.dimension = Optional.ofNullable(dimension);
    }

    @Override
    public void zine$setSmokey(@Nullable Boolean smokey) {
        this.smokey = Optional.ofNullable(smokey);
    }

    @Override
    public void zine$setLight(@Nullable LightPredicate light) {
        this.light = Optional.ofNullable(light);
    }

    @Override
    public void zine$setBlock(@Nullable BlockPredicate block) {
        this.block = Optional.ofNullable(block);
    }

    @Override
    public void zine$setFluid(@Nullable FluidPredicate fluid) {
        this.fluid = Optional.ofNullable(fluid);
    }

    @Override
    public void zine$setCanSeeSky(@Nullable Boolean canSeeSky) {
        this.canSeeSky = Optional.ofNullable(canSeeSky);
    }
}
