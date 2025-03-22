package com.eightsidedsquare.zine.common.advancement;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.entity.passive.FrogVariant;
import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;

public final class VanillaAdvancementModifications {
    private VanillaAdvancementModifications() {
    }

    public static void registerAdventuringTimeBiome(RegistryKey<Biome> biomeKey) {
        VanillaAdvancementModificationsImpl.registerAdventuringTimeBiome(biomeKey);
    }

    public static void registerAllEffectsStatusEffect(RegistryEntry<StatusEffect> statusEffect, boolean potion) {
        VanillaAdvancementModificationsImpl.registerAllEffectsStatusEffect(statusEffect, potion);
    }

    public static void registerBalancedDietFood(Item item) {
        VanillaAdvancementModificationsImpl.registerBalancedDietFood(item);
    }

    public static void registerBreedableAnimal(EntityType<?> entityType, boolean laysEgg) {
        VanillaAdvancementModificationsImpl.registerBreedableAnimal(entityType, laysEgg);
    }

    public static void registerCompleteCatalogueCatVariant(RegistryKey<CatVariant> catVariantKey) {
        VanillaAdvancementModificationsImpl.registerCompleteCatalogueCatVariant(catVariantKey);
    }

    public static void registerExploreNetherBiome(RegistryKey<Biome> biomeKey) {
        VanillaAdvancementModificationsImpl.registerExploreNetherBiome(biomeKey);
    }

    public static void registerFishyBusinessItem(Item item) {
        VanillaAdvancementModificationsImpl.registerFishyBusinessItem(item);
    }

    public static void registerFroglightsItem(Item item) {
        VanillaAdvancementModificationsImpl.registerFroglightsItem(item);
    }

    public static void registerKillableMobEntityType(EntityType<?> entityType) {
        VanillaAdvancementModificationsImpl.registerKillableMobEntityType(entityType);
    }

    public static void registerLeashAllFrogVariantsFrogVariant(RegistryKey<FrogVariant> frogVariantKey) {
        VanillaAdvancementModificationsImpl.registerLeashAllFrogVariantsFrogVariant(frogVariantKey);
    }

    public static void registerLightenUpBlock(Block block) {
        VanillaAdvancementModificationsImpl.registerLightenUpBlock(block);
    }

    public static void registerLootBastionLootTable(RegistryKey<LootTable> lootTableKey) {
        VanillaAdvancementModificationsImpl.registerLootBastionLootTable(lootTableKey);
    }

    public static void registerPlantSeedBlock(Block block, boolean fromSniffer) {
        VanillaAdvancementModificationsImpl.registerPlantSeedBlock(block, fromSniffer);
    }

    public static void registerSalvageSherdLootTable(RegistryKey<LootTable> lootTableKey) {
        VanillaAdvancementModificationsImpl.registerSalvageSherdLootTable(lootTableKey);
    }

    public static void registerScrapingAxeItem(Item item) {
        VanillaAdvancementModificationsImpl.registerScrapingAxeItem(item);
    }

    public static void registerTacticalFishingBucketItem(Item item) {
        VanillaAdvancementModificationsImpl.registerTacticalFishingBucketItem(item);
    }

    public static void registerTrimWithAnyArmorPatternRecipe(RegistryKey<Recipe<?>> recipeKey) {
        VanillaAdvancementModificationsImpl.registerTrimWithAnyArmorPatternRecipe(recipeKey);
    }

    public static void registerWaxOffBlock(Block block) {
        VanillaAdvancementModificationsImpl.registerWaxOffBlock(block);
    }

    public static void registerWaxOnBlock(Block block) {
        VanillaAdvancementModificationsImpl.registerWaxOnBlock(block);
    }

    public static void registerWholePackWolfVariant(RegistryKey<WolfVariant> wolfVariantKey) {
        VanillaAdvancementModificationsImpl.registerWholePackWolfVariant(wolfVariantKey);
    }
}
