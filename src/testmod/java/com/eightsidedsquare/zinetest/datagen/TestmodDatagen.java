package com.eightsidedsquare.zinetest.datagen;

import com.eightsidedsquare.zinetest.core.TestmodInit;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.item.equipment.trim.ArmorTrimAssets;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.item.equipment.trim.ArmorTrimPattern;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class TestmodDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(TestmodSoundListGen::new);
        pack.addProvider(TestmodRecipeGen.Provider::new);
        pack.addProvider(TestmodItemTagGen::new);
        pack.addProvider(TestmodDynamicGen::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, registerable -> {
            registerable.register(TestmodInit.TOURMALINE_TRIM_MATERIAL, new ArmorTrimMaterial(
                    ArmorTrimAssets.of("tourmaline"),
                    Text.literal("Tourmaline").fillStyle(Style.EMPTY.withColor(0x71f3bb))
            ));
            registerable.register(TestmodInit.OBSIDIAN_TRIM_MATERIAL, new ArmorTrimMaterial(
                    ArmorTrimAssets.of("obsidian"),
                    Text.literal("Obsidian").fillStyle(Style.EMPTY.withColor(0x3b2754))
            ));
        });
        registryBuilder.addRegistry(RegistryKeys.TRIM_PATTERN, registerable -> {
            registerable.register(TestmodInit.CHECKERED_TRIM_PATTERN, new ArmorTrimPattern(
                    TestmodInit.CHECKERED_TRIM_PATTERN.getValue(),
                    Text.literal("Checkered Armor Trim"),
                    false
            ));
        });
    }
}
