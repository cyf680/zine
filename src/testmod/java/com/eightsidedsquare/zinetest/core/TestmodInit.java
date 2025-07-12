package com.eightsidedsquare.zinetest.core;

import com.eightsidedsquare.zine.common.advancement.VanillaAdvancementModifications;
import com.eightsidedsquare.zine.common.block.ModifyBlockSoundGroupCallback;
import com.eightsidedsquare.zine.common.registry.RegistryHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProvidesTrimMaterialComponent;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.item.equipment.trim.ArmorTrimPattern;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorLists;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;

import java.util.List;

public class TestmodInit implements ModInitializer {

    public static final String MOD_ID = "zinetest";
    public static final RegistryHelper REGISTRY = RegistryHelper.create(MOD_ID);

    public static final RegistryKey<ArmorTrimMaterial> TOURMALINE_TRIM_MATERIAL = RegistryKey.of(RegistryKeys.TRIM_MATERIAL, id("tourmaline"));
    public static final RegistryKey<ArmorTrimMaterial> OBSIDIAN_TRIM_MATERIAL = RegistryKey.of(RegistryKeys.TRIM_MATERIAL, id("obsidian"));
    public static final RegistryKey<ArmorTrimPattern> CHECKERED_TRIM_PATTERN = RegistryKey.of(RegistryKeys.TRIM_PATTERN, id("checkered"));

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onInitialize() {
        TestmodBlocks.init();
        TestmodItems.init();

        DynamicRegistrySetupCallback.EVENT.register(registryView -> {
            registryView.registerEntryAdded(RegistryKeys.PROCESSOR_LIST, (rawId, id, processorList) -> {
                if(StructureProcessorLists.TRIAL_CHAMBERS_COPPER_BULB_DEGRADATION.getValue().equals(id)) {
                    processorList.zine$addProcessor(
                            new RuleStructureProcessor(List.of(
                                    new StructureProcessorRule(
                                            new BlockMatchRuleTest(Blocks.TUFF_BRICKS),
                                            AlwaysTrueRuleTest.INSTANCE,
                                            TestmodBlocks.GOO.getDefaultState()
                                    )
                            ))
                    );
                }
            });
        });

        DefaultItemComponentEvents.MODIFY.register(ctx -> {
            ctx.modify(Items.OBSIDIAN, builder -> builder.add(DataComponentTypes.PROVIDES_TRIM_MATERIAL, new ProvidesTrimMaterialComponent(OBSIDIAN_TRIM_MATERIAL)));
        });

        ModifyBlockSoundGroupCallback.EVENT.register(ctx -> {
            ctx.setSoundGroup(BlockSoundGroup.TUFF_BRICKS, Blocks.STONE_BRICKS, Blocks.STONE_BRICK_STAIRS, Blocks.STONE_BRICK_SLAB);
        });

        VanillaAdvancementModifications.registerTacticalFishingBucketItem(Items.AXOLOTL_BUCKET);
        VanillaAdvancementModifications.registerTrimWithAnyArmorPatternRecipe(RegistryKey.of(RegistryKeys.RECIPE, id("checkered_armor_trim_smithing_template_smithing_trim")));
    }
}
