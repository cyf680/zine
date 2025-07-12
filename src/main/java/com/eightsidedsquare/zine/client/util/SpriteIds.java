package com.eightsidedsquare.zine.client.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public final class SpriteIds {
    private SpriteIds() {
    }

    public static final Identifier ARMOR_TRIM_ATLAS = TexturedRenderLayers.ARMOR_TRIMS_ATLAS_TEXTURE;
    public static final Identifier BANNER_PATTERN_ATLAS = TexturedRenderLayers.BANNER_PATTERNS_ATLAS_TEXTURE;
    public static final Identifier BED_ATLAS = TexturedRenderLayers.BEDS_ATLAS_TEXTURE;
    @SuppressWarnings("deprecation")
    public static final Identifier BLOCK_ATLAS = SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    public static final Identifier CHEST_ATLAS = TexturedRenderLayers.CHEST_ATLAS_TEXTURE;
    public static final Identifier DECORATED_POT_ATLAS = TexturedRenderLayers.DECORATED_POT_ATLAS_TEXTURE;
    public static final Identifier SHIELD_PATTERN_ATLAS = TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE;
    public static final Identifier SHULKER_BOX_ATLAS = TexturedRenderLayers.SHULKER_BOXES_ATLAS_TEXTURE;
    public static final Identifier SIGN_ATLAS = TexturedRenderLayers.SIGNS_ATLAS_TEXTURE;

    public static SpriteIdentifier armorTrim(Identifier id) {
        return new SpriteIdentifier(ARMOR_TRIM_ATLAS, id);
    }

    public static SpriteIdentifier bannerPattern(Identifier id) {
        return new SpriteIdentifier(BANNER_PATTERN_ATLAS, id);
    }

    public static SpriteIdentifier bed(Identifier id) {
        return new SpriteIdentifier(BED_ATLAS, id);
    }

    public static SpriteIdentifier block(Identifier id) {
        return new SpriteIdentifier(BLOCK_ATLAS, id);
    }

    public static SpriteIdentifier chest(Identifier id) {
        return new SpriteIdentifier(CHEST_ATLAS, id);
    }

    public static SpriteIdentifier decoratedPot(Identifier id) {
        return new SpriteIdentifier(DECORATED_POT_ATLAS, id);
    }

    public static SpriteIdentifier shieldPattern(Identifier id) {
        return new SpriteIdentifier(SHIELD_PATTERN_ATLAS, id);
    }

    public static SpriteIdentifier shulkerBox(Identifier id) {
        return new SpriteIdentifier(SHULKER_BOX_ATLAS, id);
    }

    public static SpriteIdentifier sign(Identifier id) {
        return new SpriteIdentifier(SIGN_ATLAS, id);
    }
}
