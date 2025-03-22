package com.eightsidedsquare.zinetest.datagen;

import com.eightsidedsquare.zinetest.core.TestmodInit;
import com.eightsidedsquare.zinetest.core.TestmodItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.item.equipment.trim.ArmorTrimPattern;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.Map;

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
            registerable.register(TestmodInit.TOURMALINE_TRIM_MATERIAL, ArmorTrimMaterial.of(
                    "tourmaline",
                    TestmodItems.TOURMALINE,
                    Text.literal("Tourmaline").fillStyle(Style.EMPTY.withColor(0x71f3bb)),
                    Map.of()
            ));
            registerable.register(TestmodInit.OBSIDIAN_TRIM_MATERIAL, ArmorTrimMaterial.of(
                    "obsidian",
                    Items.OBSIDIAN,
                    Text.literal("Obsidian").fillStyle(Style.EMPTY.withColor(0x3b2754)),
                    Map.of()
            ));
        });
        registryBuilder.addRegistry(RegistryKeys.TRIM_PATTERN, registerable -> {
            registerable.register(TestmodInit.CHECKERED_TRIM_PATTERN, new ArmorTrimPattern(
                    TestmodInit.CHECKERED_TRIM_PATTERN.getValue(),
                    Registries.ITEM.getEntry(TestmodItems.CHECKERED_ARMOR_TRIM_SMITHING_TEMPLATE),
                    Text.literal("Checkered Armor Trim"),
                    false
            ));
        });
    }
}
