package com.eightsidedsquare.zinetest;

import com.eightsidedsquare.zine.client.atlas.AtlasEvents;
import com.eightsidedsquare.zine.client.atlas.ConnectedTexturesAtlasSource;
import com.eightsidedsquare.zine.client.atlas.GeneratorAtlasSource;
import com.eightsidedsquare.zine.client.atlas.RemapAtlasSource;
import com.eightsidedsquare.zine.client.atlas.generator.NoiseSpriteGenerator;
import com.eightsidedsquare.zine.client.atlas.generator.SpriteProperties;
import com.eightsidedsquare.zine.client.atlas.gradient.Gradient1D;
import com.eightsidedsquare.zine.client.block.model.FancyConnectedBlockModel;
import com.eightsidedsquare.zine.client.block.model.TessellatingBlockModel;
import com.eightsidedsquare.zine.client.item.ItemModelEvents;
import com.eightsidedsquare.zine.client.language.LanguageEvents;
import com.eightsidedsquare.zine.client.model.ModelEvents;
import com.eightsidedsquare.zine.client.rendering.ShaderUniformRegistry;
import com.eightsidedsquare.zine.client.trim.ArmorTrimRegistry;
import com.eightsidedsquare.zinetest.core.TestmodBlocks;
import com.eightsidedsquare.zinetest.core.TestmodInit;
import com.eightsidedsquare.zinetest.core.TestmodItems;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.data.*;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.model.SimpleBlockStateModel;
import net.minecraft.client.render.model.json.ModelVariant;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.TriState;
import net.minecraft.util.Util;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TestmodClient implements ClientModInitializer {

    public static int entityId;
    private static final Identifier TEST_MODEL = TestmodInit.id("item/test");
    public static final RenderPipeline CUSTOM_ENTITY_PIPELINE = RenderPipelines.register(
            RenderPipeline.builder(RenderPipelines.ENTITY_SNIPPET)
                    .withFragmentShader(TestmodInit.id("core/custom_entity"))
                    .withLocation("pipeline/entity_cutout_no_cull")
                    .withShaderDefine("ALPHA_CUTOUT", 0.1F)
                    .withSampler("Sampler1")
                    .withUniform("ScreenSize", UniformType.VEC2)
                    .withUniform("EntityId", UniformType.INT)
                    .withCull(false)
                    .build()
    );
    public static final Function<Identifier, RenderLayer> CUSTOM_ENTITY = Util.memoize(
            (texture) -> {
                RenderLayer.MultiPhaseParameters multiPhaseParameters = RenderLayer.MultiPhaseParameters.builder()
                        .texture(new RenderPhase.Texture(texture, TriState.FALSE, false))
                        .lightmap(RenderPhase.ENABLE_LIGHTMAP)
                        .overlay(RenderPhase.ENABLE_OVERLAY_COLOR)
                        .build(true);
                return RenderLayer.of(
                        "custom_entity",
                        1536,
                        true,
                        false,
                        CUSTOM_ENTITY_PIPELINE,
                        multiPhaseParameters
                );
            }
    );

    @Override
    public void onInitializeClient() {
        ShaderUniformRegistry.INSTANCE.register("EntityId", ctx -> ctx.uniform().set(entityId));
        AtlasEvents.modifySourcesEvent(Identifier.ofVanilla("blocks")).register(sources -> {
            sources.add(new GeneratorAtlasSource(
                    new NoiseSpriteGenerator(
                            Gradient1D.builder()
                                    .pt(0xff1a372c, 0.2f)
                                    .pt(0xff42bb1f, 0.6f)
                                    .pt(0xffcaf732, 0.9f)
                                    .build(),
                            new DoublePerlinNoiseSampler.NoiseParameters(1, 1, 1, 1),
                            42069L,
                            new Vector2f(3f, 2f),
                            new Vector3f(0, -5, 1f),
                            0.05f
                    ),
                    TestmodInit.id("goo"),
                    new SpriteProperties(16, 16, 64, 2, false)
            ));
            sources.add(new RemapAtlasSource(
                    List.of(
                            RemapAtlasSource.textureSet(
                                    "polished_granite",
                                    Map.of(
                                            0, RemapAtlasSource.texture(
                                                    Identifier.of("block/debug"),
                                                    TestmodInit.id("block/offset"),
                                                    -16,
                                                    16
                                            ),
                                            255, RemapAtlasSource.texture(Identifier.of("block/tuff"))
                                    )
                            )

                    ),
                    List.of(
                            RemapAtlasSource.mapping(TestmodInit.id("block/bricks_uv"), null, "_bricks"),
                            RemapAtlasSource.mapping(TestmodInit.id("block/stone_bricks_uv"), null, "_stone_bricks"),
                            RemapAtlasSource.mapping(TestmodInit.id("block/mud_bricks_uv"), null, "_mud_bricks"),
                            RemapAtlasSource.mapping(TestmodInit.id("block/tiles_uv"), null, "_tiles"),
                            RemapAtlasSource.mapping(TestmodInit.id("block/cut_uv"), "cut_", null),
                            RemapAtlasSource.mapping(TestmodInit.id("block/full_uv"), null, "_block")
                    )
            ));
            sources.add(new ConnectedTexturesAtlasSource(TestmodInit.id("block/wood")));
        });
        ItemModelEvents.BEFORE_BAKE.register((id, unbaked) -> {
            if(Items.DIAMOND.zine$modelEquals(id)) {
                return ItemModels.composite(unbaked, ItemModels.basic(TEST_MODEL));
            }
            return unbaked;
        });
        ModelEvents.ADD_UNBAKED.register(modelCollector -> {
            Models.GENERATED.upload(TEST_MODEL, TextureMap.layer0(Items.EMERALD), modelCollector);
            Models.GENERATED.upload(TestmodItems.TOURMALINE, TextureMap.layer0(TestmodItems.TOURMALINE), modelCollector);
            Models.GENERATED.upload(TestmodItems.CHECKERED_ARMOR_TRIM_SMITHING_TEMPLATE, TextureMap.layer0(TestmodItems.CHECKERED_ARMOR_TRIM_SMITHING_TEMPLATE), modelCollector);
            Models.CUBE_ALL.upload(TestmodBlocks.TOURMALINE_BLOCK, TextureMap.all(TestmodBlocks.TOURMALINE_BLOCK), modelCollector);
            Models.CUBE_ALL.upload(TestmodBlocks.GOO, TextureMap.all(TestmodInit.id("goo")), modelCollector);
            Models.CUBE_ALL.upload(TestmodBlocks.WOOD, TextureMap.all(TextureMap.getSubId(TestmodBlocks.WOOD, "_all")), modelCollector);
            Models.CUBE_ALL.upload(TestmodBlocks.RAINBOW, TextureMap.all(TextureMap.getId(TestmodBlocks.RAINBOW)), modelCollector);
        });
        ItemModelEvents.ADD_UNBAKED.register(assetCollector -> {
            assetCollector.accept(TestmodItems.TOURMALINE, ItemModels.basic(ModelIds.getItemModelId(TestmodItems.TOURMALINE)));
            assetCollector.accept(TestmodItems.CHECKERED_ARMOR_TRIM_SMITHING_TEMPLATE, ItemModels.basic(ModelIds.getItemModelId(TestmodItems.CHECKERED_ARMOR_TRIM_SMITHING_TEMPLATE)));
            assetCollector.accept(TestmodItems.TOURMALINE_BLOCK, ItemModels.basic(ModelIds.getBlockModelId(TestmodBlocks.TOURMALINE_BLOCK)));
            assetCollector.accept(TestmodItems.GOO, ItemModels.basic(ModelIds.getBlockModelId(TestmodBlocks.GOO)));
            assetCollector.accept(TestmodItems.WOOD, ItemModels.basic(ModelIds.getBlockModelId(TestmodBlocks.WOOD)));
            assetCollector.accept(TestmodItems.RAINBOW, ItemModels.basic(ModelIds.getBlockModelId(TestmodBlocks.RAINBOW)));
        });
        ModelLoadingPlugin.register(pluginCtx -> {
            pluginCtx.registerBlockStateResolver(TestmodBlocks.TOURMALINE_BLOCK, ctx -> {
                ctx.setModel(ctx.block().getDefaultState(), new SimpleBlockStateModel.Unbaked(new ModelVariant(ModelIds.getBlockModelId(TestmodBlocks.TOURMALINE_BLOCK))).cached());
            });
            pluginCtx.registerBlockStateResolver(TestmodBlocks.GOO, ctx -> {
                ctx.setModel(ctx.block().getDefaultState(), new SimpleBlockStateModel.Unbaked(new ModelVariant(ModelIds.getBlockModelId(TestmodBlocks.GOO))).cached());
            });
            pluginCtx.registerBlockStateResolver(TestmodBlocks.WOOD, ctx -> {
                ctx.setModel(ctx.block().getDefaultState(), new FancyConnectedBlockModel(TestmodInit.id("block/wood")).cached());
            });
            pluginCtx.registerBlockStateResolver(TestmodBlocks.RAINBOW, ctx -> {
                ctx.setModel(ctx.block().getDefaultState(), new TessellatingBlockModel(
                        TestmodInit.id("block/rainbow"),
                        4
                ).cached());
            });
        });
        ModelLoadingPlugin.register(ctx -> ctx.modifyItemModelBeforeBake().register((model, context) -> {
            if(Items.DIAMOND.zine$modelEquals(context.itemId())) {
                return ItemModels.composite(model, ItemModels.basic(TEST_MODEL));
            }
            return model;
        }));
        LanguageEvents.MODIFY_TRANSLATIONS.register((translations, languageCode, rightToLeft) -> {
            translations.putIfAbsent(TestmodItems.TOURMALINE.getTranslationKey(), "Tourmaline");
            translations.putIfAbsent(TestmodItems.CHECKERED_ARMOR_TRIM_SMITHING_TEMPLATE.getTranslationKey(), "Checkered Armor Trim");
            translations.putIfAbsent(TestmodItems.TOURMALINE_BLOCK.getTranslationKey(), "Block of Tourmaline");
            translations.putIfAbsent(TestmodItems.GOO.getTranslationKey(), "Goo");
            translations.putIfAbsent(TestmodItems.WOOD.getTranslationKey(), "Wood");
            translations.putIfAbsent(TestmodItems.RAINBOW.getTranslationKey(), "Rainbow");
        });
        ArmorTrimRegistry.registerMaterial(TestmodInit.TOURMALINE_TRIM_MATERIAL);
        ArmorTrimRegistry.registerMaterial(TestmodInit.OBSIDIAN_TRIM_MATERIAL);
        ArmorTrimRegistry.registerPattern(TestmodInit.CHECKERED_TRIM_PATTERN);
    }
}
