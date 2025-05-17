package com.eightsidedsquare.zine.common.advancement;

import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.entity.passive.FrogVariant;
import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.predicate.component.ComponentMapPredicate;
import net.minecraft.predicate.component.ComponentsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

public final class VanillaAdvancementModificationsImpl {
    private VanillaAdvancementModificationsImpl() {
    }

    private static final Identifier ADVENTURING_TIME_ID = Identifier.ofVanilla("adventure/adventuring_time");
    private static final Identifier ALL_EFFECTS_ID = Identifier.ofVanilla("nether/all_effects");
    private static final Identifier ALL_POTIONS_ID = Identifier.ofVanilla("nether/all_potions");
    private static final Identifier BALANCED_DIET_ID = Identifier.ofVanilla("husbandry/balanced_diet");
    private static final Identifier BRED_ALL_ANIMALS_ID = Identifier.ofVanilla("husbandry/bred_all_animals");
    private static final Identifier COMPLETE_CATALOGUE_ID = Identifier.ofVanilla("husbandry/complete_catalogue");
    private static final Identifier EXPLORE_NETHER_ID = Identifier.ofVanilla("nether/explore_nether");
    private static final Identifier FISHY_BUSINESS_ID = Identifier.ofVanilla("husbandry/fishy_business");
    private static final Identifier FROGLIGHTS_ID = Identifier.ofVanilla("husbandry/froglights");
    private static final Identifier KILL_A_MOB_ID = Identifier.ofVanilla("adventure/kill_a_mob");
    private static final Identifier KILL_ALL_MOBS_ID = Identifier.ofVanilla("adventure/kill_all_mobs");
    private static final Identifier LEASH_ALL_FROG_VARIANTS_ID = Identifier.ofVanilla("husbandry/leash_all_frog_variants");
    private static final Identifier LIGHTEN_UP_ID = Identifier.ofVanilla("adventure/lighten_up");
    private static final Identifier LOOT_BASTION_ID = Identifier.ofVanilla("nether/loot_bastion");
    private static final Identifier PLANT_ANY_SNIFFER_SEED_ID = Identifier.ofVanilla("husbandry/plant_any_sniffer_seed");
    private static final Identifier PLANT_SEED_ID = Identifier.ofVanilla("husbandry/plant_seed");
    private static final Identifier SALVAGE_SHERD_ID = Identifier.ofVanilla("adventure/salvage_sherd");
    private static final Identifier TACTICAL_FISHING_ID = Identifier.ofVanilla("husbandry/tactical_fishing");
    private static final Identifier TRIM_WITH_ANY_ARMOR_PATTERN_ID = Identifier.ofVanilla("adventure/trim_with_any_armor_pattern");
    private static final Identifier WAX_OFF_ID = Identifier.ofVanilla("husbandry/wax_off");
    private static final Identifier WAX_ON_ID = Identifier.ofVanilla("husbandry/wax_on");
    private static final Identifier WHOLE_PACK_ID = Identifier.ofVanilla("husbandry/whole_pack");

    private static final TreeSet<RegistryKey<Biome>> ADVENTURING_TIME_BIOMES = registryKeySet();
    private static final Reference2BooleanMap<RegistryEntry<StatusEffect>> ALL_EFFECTS_STATUS_EFFECTS = new Reference2BooleanOpenHashMap<>();
    private static final TreeSet<Item> BALANCED_DIET_ITEMS = set(Registries.ITEM);
    private static final Reference2BooleanMap<EntityType<?>> BREEDABLE_ANIMALS = new Reference2BooleanOpenHashMap<>();
    private static final TreeSet<RegistryKey<CatVariant>> COMPLETE_CATALOGUE_CAT_VARIANTS = registryKeySet();
    private static final TreeSet<RegistryKey<Biome>> EXPLORE_NETHER_BIOMES = registryKeySet();
    private static final TreeSet<Item> FISHY_BUSINESS_ITEMS = set(Registries.ITEM);
    private static final TreeSet<Item> FROGLIGHTS_ITEMS = set(Registries.ITEM);
    private static final TreeSet<EntityType<?>> KILLABLE_MOBS = set(Registries.ENTITY_TYPE);
    private static final TreeSet<RegistryKey<FrogVariant>> LEASH_ALL_FROG_VARIANTS_FROG_VARIANTS = registryKeySet();
    private static final TreeSet<Block> LIGHTEN_UP_BLOCKS = set(Registries.BLOCK);
    private static final TreeSet<RegistryKey<LootTable>> LOOT_BASTION_LOOT_TABLES = registryKeySet();
    private static final Reference2BooleanMap<Block> PLANT_SEED_BLOCKS = new Reference2BooleanOpenHashMap<>();
    private static final TreeSet<RegistryKey<LootTable>> SALVAGE_SHERD_LOOT_TABLES = registryKeySet();
    private static final TreeSet<Item> SCRAPING_AXE_ITEMS = set(Registries.ITEM);
    private static final TreeSet<Item> TACTICAL_FISHING_ITEMS = set(Registries.ITEM);
    private static final TreeSet<RegistryKey<Recipe<?>>> TRIM_WITH_ANY_ARMOR_PATTERN_RECIPES = registryKeySet();
    private static final TreeSet<Block> WAX_OFF_BLOCKS = set(Registries.BLOCK);
    private static final TreeSet<Block> WAX_ON_BLOCKS = set(Registries.BLOCK);
    private static final TreeSet<RegistryKey<WolfVariant>> WHOLE_PACK_WOLF_VARIANTS = registryKeySet();

    public static void registerEvents() {
        AdvancementEvents.modifyAdvancement(ADVENTURING_TIME_ID, VanillaAdvancementModificationsImpl::modifyAdventuringTime);
        AdvancementEvents.modifyAdvancement(ALL_EFFECTS_ID, VanillaAdvancementModificationsImpl::modifyAllEffects);
        AdvancementEvents.modifyAdvancement(ALL_POTIONS_ID, VanillaAdvancementModificationsImpl::modifyAllPotions);
        AdvancementEvents.modifyAdvancement(BALANCED_DIET_ID, VanillaAdvancementModificationsImpl::modifyBalancedDiet);
        AdvancementEvents.modifyAdvancement(BRED_ALL_ANIMALS_ID, VanillaAdvancementModificationsImpl::modifyBredAllAnimals);
        AdvancementEvents.modifyAdvancement(COMPLETE_CATALOGUE_ID, VanillaAdvancementModificationsImpl::modifyCompleteCatalogue);
        AdvancementEvents.modifyAdvancement(EXPLORE_NETHER_ID, VanillaAdvancementModificationsImpl::modifyExploreNether);
        AdvancementEvents.modifyAdvancement(FISHY_BUSINESS_ID, VanillaAdvancementModificationsImpl::modifyFishyBusiness);
        AdvancementEvents.modifyAdvancement(FROGLIGHTS_ID, VanillaAdvancementModificationsImpl::modifyFroglights);
        AdvancementEvents.modifyAdvancement(KILL_A_MOB_ID, VanillaAdvancementModificationsImpl::modifyKillAMob);
        AdvancementEvents.modifyAdvancement(KILL_ALL_MOBS_ID, VanillaAdvancementModificationsImpl::modifyKillAllMobs);
        AdvancementEvents.modifyAdvancement(LEASH_ALL_FROG_VARIANTS_ID, VanillaAdvancementModificationsImpl::modifyLeashAllFrogVariants);
        AdvancementEvents.modifyAdvancement(LIGHTEN_UP_ID, VanillaAdvancementModificationsImpl::modifyLightenUp);
        AdvancementEvents.modifyAdvancement(LOOT_BASTION_ID, VanillaAdvancementModificationsImpl::modifyLootBastion);
        AdvancementEvents.modifyAdvancement(PLANT_ANY_SNIFFER_SEED_ID, VanillaAdvancementModificationsImpl::modifyPlantAnySnifferSeed);
        AdvancementEvents.modifyAdvancement(PLANT_SEED_ID, VanillaAdvancementModificationsImpl::modifyPlantSeed);
        AdvancementEvents.modifyAdvancement(SALVAGE_SHERD_ID, VanillaAdvancementModificationsImpl::modifySalvageSherd);
        AdvancementEvents.modifyAdvancement(TACTICAL_FISHING_ID, VanillaAdvancementModificationsImpl::modifyTacticalFishing);
        AdvancementEvents.modifyAdvancement(TRIM_WITH_ANY_ARMOR_PATTERN_ID, VanillaAdvancementModificationsImpl::modifyTrimWithAnyArmorPattern);
        AdvancementEvents.modifyAdvancement(WAX_OFF_ID, VanillaAdvancementModificationsImpl::modifyWaxOff);
        AdvancementEvents.modifyAdvancement(WAX_ON_ID, VanillaAdvancementModificationsImpl::modifyWaxOn);
        AdvancementEvents.modifyAdvancement(WHOLE_PACK_ID, VanillaAdvancementModificationsImpl::modifyWholePack);
    }

    public static void registerAdventuringTimeBiome(RegistryKey<Biome> biomeKey) {
        ADVENTURING_TIME_BIOMES.add(biomeKey);
    }

    public static void registerAllEffectsStatusEffect(RegistryEntry<StatusEffect> statusEffect, boolean potion) {
        ALL_EFFECTS_STATUS_EFFECTS.put(statusEffect, potion);
    }

    public static void registerBalancedDietFood(Item item) {
        BALANCED_DIET_ITEMS.add(item);
    }

    public static void registerBreedableAnimal(EntityType<?> entityType, boolean laysEgg) {
        BREEDABLE_ANIMALS.put(entityType, laysEgg);
    }

    public static void registerCompleteCatalogueCatVariant(RegistryKey<CatVariant> catVariantKey) {
        COMPLETE_CATALOGUE_CAT_VARIANTS.add(catVariantKey);
    }

    public static void registerExploreNetherBiome(RegistryKey<Biome> biomeKey) {
        EXPLORE_NETHER_BIOMES.add(biomeKey);
    }

    public static void registerFishyBusinessItem(Item item) {
        FISHY_BUSINESS_ITEMS.add(item);
    }

    public static void registerFroglightsItem(Item item) {
        FROGLIGHTS_ITEMS.add(item);
    }

    public static void registerKillableMobEntityType(EntityType<?> entityType) {
        KILLABLE_MOBS.add(entityType);
    }

    public static void registerLeashAllFrogVariantsFrogVariant(RegistryKey<FrogVariant> frogVariantKey) {
        LEASH_ALL_FROG_VARIANTS_FROG_VARIANTS.add(frogVariantKey);
    }

    public static void registerLightenUpBlock(Block block) {
        LIGHTEN_UP_BLOCKS.add(block);
    }

    public static void registerLootBastionLootTable(RegistryKey<LootTable> lootTableKey) {
        LOOT_BASTION_LOOT_TABLES.add(lootTableKey);
    }

    public static void registerPlantSeedBlock(Block block, boolean fromSniffer) {
        PLANT_SEED_BLOCKS.put(block, fromSniffer);
    }

    public static void registerSalvageSherdLootTable(RegistryKey<LootTable> lootTableKey) {
        SALVAGE_SHERD_LOOT_TABLES.add(lootTableKey);
    }

    public static void registerScrapingAxeItem(Item item) {
        SCRAPING_AXE_ITEMS.add(item);
    }

    public static void registerTacticalFishingBucketItem(Item item) {
        TACTICAL_FISHING_ITEMS.add(item);
    }

    public static void registerTrimWithAnyArmorPatternRecipe(RegistryKey<Recipe<?>> recipeKey) {
        TRIM_WITH_ANY_ARMOR_PATTERN_RECIPES.add(recipeKey);
    }

    public static void registerWaxOffBlock(Block block) {
        WAX_OFF_BLOCKS.add(block);
    }

    public static void registerWaxOnBlock(Block block) {
        WAX_ON_BLOCKS.add(block);
    }

    public static void registerWholePackWolfVariant(RegistryKey<WolfVariant> wolfVariantKey) {
        WHOLE_PACK_WOLF_VARIANTS.add(wolfVariantKey);
    }

    private static Advancement modifyAdventuringTime(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        lookup.getOptional(RegistryKeys.BIOME).ifPresent(biomeLookup -> {
            for(RegistryKey<Biome> biomeKey : ADVENTURING_TIME_BIOMES) {
                Optional<RegistryEntry.Reference<Biome>> biome = biomeLookup.getOptional(biomeKey);
                if(biome.isPresent()) {
                    String name = biomeKey.getValue().toString();
                    advancement.zine$addCriterion(name, TickCriterion.Conditions.createLocation(
                            LocationPredicate.Builder.createBiome(biome.get())
                    ));
                    advancement.requirements().zine$addRequirement(List.of(name));
                }
            }
        });
        return advancement;
    }

    private static Advancement modifyAllEffects(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        advancement.zine$getCriterion("all_effects", Criteria.EFFECTS_CHANGED)
                .flatMap(EffectsChangedCriterion.Conditions::effects)
                .ifPresent(predicate -> ALL_EFFECTS_STATUS_EFFECTS.keySet().forEach(predicate::zine$addEffect));
        return advancement;
    }

    private static Advancement modifyAllPotions(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        advancement.zine$getCriterion("all_effects", Criteria.EFFECTS_CHANGED)
                .flatMap(EffectsChangedCriterion.Conditions::effects)
                .ifPresent(predicate -> ALL_EFFECTS_STATUS_EFFECTS.forEach((statusEffect, potion) -> {
                    if(potion) {
                        predicate.zine$addEffect(statusEffect);
                    }
                }));
        return advancement;
    }

    private static Advancement modifyBalancedDiet(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        for(Item item : BALANCED_DIET_ITEMS) {
            String name = Registries.ITEM.getId(item).toString();
            advancement.zine$addCriterion(name, ConsumeItemCriterion.Conditions.item(Registries.ITEM, item));
            advancement.requirements().zine$addRequirement(List.of(name));
        }
        return advancement;
    }

    private static Advancement modifyBredAllAnimals(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        for(Reference2BooleanMap.Entry<EntityType<?>> entry : BREEDABLE_ANIMALS.reference2BooleanEntrySet()) {
            EntityType<?> entityType = entry.getKey();
            boolean laysEgg = entry.getBooleanValue();
            String name = Registries.ENTITY_TYPE.getId(entityType).toString();

            if(laysEgg) {
                advancement.zine$addCriterion(name, BredAnimalsCriterion.Conditions.create(
                        Optional.of(EntityPredicate.Builder.create().type(Registries.ENTITY_TYPE, entityType).build()),
                        Optional.of(EntityPredicate.Builder.create().type(Registries.ENTITY_TYPE, entityType).build()),
                        Optional.empty()
                ));
            }else {
                advancement.zine$addCriterion(name, BredAnimalsCriterion.Conditions.create(
                        EntityPredicate.Builder.create().type(Registries.ENTITY_TYPE, entityType)
                ));
            }
            advancement.requirements().zine$addRequirement(List.of(name));
        }
        return advancement;
    }

    private static Advancement modifyCompleteCatalogue(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        lookup.getOptional(RegistryKeys.CAT_VARIANT).ifPresent(catVariantLookup -> {
            for(RegistryKey<CatVariant> variantKey : COMPLETE_CATALOGUE_CAT_VARIANTS) {
                Optional<RegistryEntry.Reference<CatVariant>> variant = catVariantLookup.getOptional(variantKey);
                if(variant.isPresent()) {
                    String name = variantKey.getValue().toString();
                    advancement.zine$addCriterion(name, TameAnimalCriterion.Conditions.create(
                            EntityPredicate.Builder.create().components(
                                    ComponentsPredicate.Builder.create()
                                            .exact(ComponentMapPredicate.of(DataComponentTypes.CAT_VARIANT, variant.get()))
                                            .build()
                            )
                    ));
                    advancement.requirements().zine$addRequirement(List.of(name));
                }
            }
        });
        return advancement;
    }

    private static Advancement modifyExploreNether(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        lookup.getOptional(RegistryKeys.BIOME).ifPresent(biomeLookup -> {
            for(RegistryKey<Biome> biomeKey : EXPLORE_NETHER_BIOMES) {
                Optional<RegistryEntry.Reference<Biome>> biome = biomeLookup.getOptional(biomeKey);
                if(biome.isPresent()) {
                    String name = biomeKey.getValue().toString();
                    advancement.zine$addCriterion(name, TickCriterion.Conditions.createLocation(
                            LocationPredicate.Builder.createBiome(biome.get())
                    ));
                    advancement.requirements().zine$addRequirement(List.of(name));
                }
            }
        });
        return advancement;
    }

    private static Advancement modifyFishyBusiness(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        for(Item item : FISHY_BUSINESS_ITEMS) {
            String name = Registries.ITEM.getId(item).toString();
            advancement.zine$addCriterion(name, FishingRodHookedCriterion.Conditions.create(
                    Optional.empty(),
                    Optional.empty(),
                    Optional.of(ItemPredicate.Builder.create().items(Registries.ITEM, item).build())
            ));
            advancement.requirements().zine$addRequirement(0, name);
        }
        return advancement;
    }

    private static Advancement modifyFroglights(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        advancement.zine$getCriterion("froglights", Criteria.INVENTORY_CHANGED).ifPresent(conditions ->
                FROGLIGHTS_ITEMS.forEach(item ->
                        conditions.zine$addItem(ItemPredicate.Builder.create().items(Registries.ITEM, item).build())
                )
        );
        return advancement;
    }

    private static Advancement modifyKillAMob(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        for(EntityType<?> entityType : KILLABLE_MOBS) {
            String name = Registries.ENTITY_TYPE.getId(entityType).toString();
            advancement.zine$addCriterion(name, OnKilledCriterion.Conditions.createPlayerKilledEntity(
                    EntityPredicate.Builder.create().type(Registries.ENTITY_TYPE, entityType)
            ));
            advancement.requirements().zine$addRequirement(0, name);
        }
        return advancement;
    }

    private static Advancement modifyKillAllMobs(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        for(EntityType<?> entityType : KILLABLE_MOBS) {
            String name = Registries.ENTITY_TYPE.getId(entityType).toString();
            advancement.zine$addCriterion(name, OnKilledCriterion.Conditions.createPlayerKilledEntity(
                    EntityPredicate.Builder.create().type(Registries.ENTITY_TYPE, entityType)
            ));
            advancement.requirements().zine$addRequirement(List.of(name));
        }
        return advancement;
    }

    private static Advancement modifyLeashAllFrogVariants(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        lookup.getOptional(RegistryKeys.FROG_VARIANT).ifPresent(frogVariantLookup -> {
            ItemPredicate.Builder leadPredicate = ItemPredicate.Builder.create().items(Registries.ITEM, Items.LEAD);
            for(RegistryKey<FrogVariant> variantKey : LEASH_ALL_FROG_VARIANTS_FROG_VARIANTS) {
                Optional<RegistryEntry.Reference<FrogVariant>> variant = frogVariantLookup.getOptional(variantKey);
                if(variant.isPresent()) {
                    String name = variantKey.getValue().toString();
                    advancement.zine$addCriterion(name, PlayerInteractedWithEntityCriterion.Conditions.create(
                            leadPredicate,
                            Optional.of(
                                    EntityPredicate.contextPredicateFromEntityPredicate(
                                            EntityPredicate.Builder.create()
                                                    .type(Registries.ENTITY_TYPE, EntityType.FROG)
                                                    .components(ComponentsPredicate.Builder.create()
                                                            .exact(ComponentMapPredicate.of(DataComponentTypes.FROG_VARIANT, variant.get()))
                                                            .build()
                                                    )
                                    )
                            )
                    ));
                    advancement.requirements().zine$addRequirement(List.of(name));
                }
            }
        });
        return advancement;
    }

    private static Advancement modifyLightenUp(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        advancement.zine$getCriterion("lighten_up", Criteria.ITEM_USED_ON_BLOCK)
                .flatMap(ItemCriterion.Conditions::location)
                .ifPresent(predicate -> {
                    for(LootCondition lootCondition : predicate.zine$getConditions()) {
                        if(lootCondition instanceof LocationCheckLootCondition locationCondition) {
                            locationCondition.predicate()
                                    .flatMap(LocationPredicate::block)
                                    .ifPresent(blockPredicate -> blockPredicate.zine$addBlocks(LIGHTEN_UP_BLOCKS));
                        }else if(lootCondition instanceof MatchToolLootCondition(Optional<ItemPredicate> optional)) {
                            optional.ifPresent(itemPredicate -> itemPredicate.zine$addItems(SCRAPING_AXE_ITEMS));
                        }
                    }
                });
        return advancement;
    }

    private static Advancement modifyLootBastion(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        for(RegistryKey<LootTable> lootTableKey : LOOT_BASTION_LOOT_TABLES) {
            String name = lootTableKey.getValue().toString();
            advancement.zine$addCriterion(name, PlayerGeneratesContainerLootCriterion.Conditions.create(lootTableKey));
            advancement.requirements().zine$addRequirement(0, name);
        }
        return advancement;
    }

    private static Advancement modifyPlantAnySnifferSeed(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        for(Reference2BooleanMap.Entry<Block> entry : PLANT_SEED_BLOCKS.reference2BooleanEntrySet()) {
            if(!entry.getBooleanValue()) {
                continue;
            }
            Block block = entry.getKey();
            String name = Registries.BLOCK.getId(block).toString();
            advancement.zine$addCriterion(name, ItemCriterion.Conditions.createPlacedBlock(block));
            advancement.requirements().zine$addRequirement(0, name);
        }
        return advancement;
    }

    private static Advancement modifyPlantSeed(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        for(Block block : PLANT_SEED_BLOCKS.keySet()) {
            String name = Registries.BLOCK.getId(block).toString();
            advancement.zine$addCriterion(name, ItemCriterion.Conditions.createPlacedBlock(block));
            advancement.requirements().zine$addRequirement(0, name);
        }
        return advancement;
    }

    private static Advancement modifySalvageSherd(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        for(RegistryKey<LootTable> lootTableKey : SALVAGE_SHERD_LOOT_TABLES) {
            String name = lootTableKey.getValue().toString();
            advancement.zine$addCriterion(name, PlayerGeneratesContainerLootCriterion.Conditions.create(lootTableKey));
            advancement.requirements().zine$addRequirement(0, name);
        }
        return advancement;
    }

    private static Advancement modifyTacticalFishing(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        for(Item item : TACTICAL_FISHING_ITEMS) {
            String name = Registries.ITEM.getId(item).toString();
            advancement.zine$addCriterion(name, FilledBucketCriterion.Conditions.create(
                    ItemPredicate.Builder.create().items(Registries.ITEM, item)
            ));
            advancement.requirements().zine$addRequirement(0, name);
        }
        return advancement;
    }

    private static Advancement modifyTrimWithAnyArmorPattern(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        for(RegistryKey<Recipe<?>> recipeKey : TRIM_WITH_ANY_ARMOR_PATTERN_RECIPES) {
            String name = "armor_trimmed_" + recipeKey.getValue().toString();
            advancement.zine$addCriterion(name, RecipeCraftedCriterion.Conditions.create(recipeKey));
            advancement.requirements().zine$addRequirement(0, name);
        }
        return advancement;
    }

    private static Advancement modifyWaxOff(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        advancement.zine$getCriterion("wax_off", Criteria.ITEM_USED_ON_BLOCK)
                .flatMap(ItemCriterion.Conditions::location)
                .ifPresent(predicate -> {
                    for(LootCondition lootCondition : predicate.zine$getConditions()) {
                        if(lootCondition instanceof LocationCheckLootCondition locationCondition) {
                            locationCondition.predicate()
                                    .flatMap(LocationPredicate::block)
                                    .ifPresent(blockPredicate -> blockPredicate.zine$addBlocks(WAX_OFF_BLOCKS));
                        }else if(lootCondition instanceof MatchToolLootCondition(Optional<ItemPredicate> optional)) {
                            optional.ifPresent(itemPredicate -> itemPredicate.zine$addItems(SCRAPING_AXE_ITEMS));
                        }
                    }
                });
        return advancement;
    }

    private static Advancement modifyWaxOn(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        advancement.zine$getCriterion("wax_on", Criteria.ITEM_USED_ON_BLOCK)
                .flatMap(ItemCriterion.Conditions::location)
                .ifPresent(predicate -> {
                    for(LootCondition lootCondition : predicate.zine$getConditions()) {
                        if(lootCondition instanceof LocationCheckLootCondition locationCondition) {
                            locationCondition.predicate()
                                    .flatMap(LocationPredicate::block)
                                    .ifPresent(blockPredicate -> blockPredicate.zine$addBlocks(WAX_ON_BLOCKS));
                        }
                    }
                });
        return advancement;
    }

    private static Advancement modifyWholePack(Advancement advancement, RegistryWrapper.WrapperLookup lookup) {
        lookup.getOptional(RegistryKeys.WOLF_VARIANT).ifPresent(wolfVariantLookup -> {
            for(RegistryKey<WolfVariant> variantKey : WHOLE_PACK_WOLF_VARIANTS) {
                Optional<RegistryEntry.Reference<WolfVariant>> variant = wolfVariantLookup.getOptional(variantKey);
                if(variant.isPresent()) {
                    String name = variantKey.getValue().toString();
                    advancement.zine$addCriterion(name, TameAnimalCriterion.Conditions.create(
                            EntityPredicate.Builder.create().components(
                                    ComponentsPredicate.Builder.create()
                                            .exact(ComponentMapPredicate.of(DataComponentTypes.WOLF_VARIANT, variant.get()))
                                            .build()
                            )
                    ));
                    advancement.requirements().zine$addRequirement(List.of(name));
                }
            }
        });
        return advancement;
    }

    private static <T> TreeSet<T> set(Registry<T> registry) {
        return new TreeSet<>(Comparator.comparingInt(registry::getRawId));
    }

    private static <T> TreeSet<RegistryKey<T>> registryKeySet() {
        return new TreeSet<>(Comparator.comparing(RegistryKey::getValue));
    }
}
