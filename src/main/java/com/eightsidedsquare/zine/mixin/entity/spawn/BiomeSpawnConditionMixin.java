package com.eightsidedsquare.zine.mixin.entity.spawn;

import com.eightsidedsquare.zine.common.entity.spawn.ZineBiomeSpawnCondition;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.entity.spawn.BiomeSpawnCondition;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.function.Function;

@Mixin(BiomeSpawnCondition.class)
public abstract class BiomeSpawnConditionMixin implements ZineBiomeSpawnCondition {

    @Shadow @Final @Mutable
    private RegistryEntryList<Biome> requiredBiomes;

    @Override
    public void zine$setRequiredBiomes(RegistryEntryList<Biome> requiredBiomes) {
        this.requiredBiomes = requiredBiomes;
    }

    @Override
    public void zine$addRequiredBiome(RegistryEntry<Biome> requiredBiome) {
        this.requiredBiomes = ZineUtil.mergeValue(this.requiredBiomes, Function.identity(), requiredBiome);
    }

    @Override
    public void zine$addRequiredBiome(RegistryEntryLookup<Biome> biomeLookup, RegistryKey<Biome> requiredBiome) {
        this.requiredBiomes = ZineUtil.mergeValue(this.requiredBiomes, biomeLookup::getOrThrow, requiredBiome);
    }

    @Override
    public void zine$addRequiredBiomes(RegistryEntryList<Biome> requiredBiomes) {
        this.requiredBiomes = ZineUtil.mergeValues(this.requiredBiomes, requiredBiomes);
    }

    @Override
    public void zine$addRequiredBiomes(RegistryEntryLookup<Biome> biomeLookup, Collection<RegistryKey<Biome>> requiredBiomes) {
        this.requiredBiomes = ZineUtil.mergeValues(this.requiredBiomes, biomeLookup::getOrThrow, requiredBiomes);
    }
}
