package com.eightsidedsquare.zinetest.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class TestmodDynamicGen extends FabricDynamicRegistryProvider {

    public TestmodDynamicGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        this.add(entries, RegistryKeys.TRIM_MATERIAL);
        this.add(entries, RegistryKeys.TRIM_PATTERN);
    }

    private <T> void add(Entries entries, RegistryKey<Registry<T>> key) {
        entries.addAll((RegistryWrapper.Impl<T>) entries.getLookup(key));
    }

    @Override
    public String getName() {
        return "Dynamic";
    }
}
