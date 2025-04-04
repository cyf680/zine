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

    public static SpriteIdentifier armorTrim(Identifier id) {
        return new SpriteIdentifier(TexturedRenderLayers.ARMOR_TRIMS_ATLAS_TEXTURE, id);
    }

    public static SpriteIdentifier bed(Identifier id) {
        return new SpriteIdentifier(TexturedRenderLayers.BEDS_ATLAS_TEXTURE, id);
    }

    @SuppressWarnings("deprecation")
    public static SpriteIdentifier block(Identifier id) {
        return new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, id);
    }

    public static SpriteIdentifier chest(Identifier id) {
        return new SpriteIdentifier(TexturedRenderLayers.CHEST_ATLAS_TEXTURE, id);
    }

    public static SpriteIdentifier decoratedPot(Identifier id) {
        return new SpriteIdentifier(TexturedRenderLayers.DECORATED_POT_ATLAS_TEXTURE, id);
    }

    public static SpriteIdentifier shieldPattern(Identifier id) {
        return new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, id);
    }

    public static SpriteIdentifier shulkerBox(Identifier id) {
        return new SpriteIdentifier(TexturedRenderLayers.SHULKER_BOXES_ATLAS_TEXTURE, id);
    }

    public static SpriteIdentifier sign(Identifier id) {
        return new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, id);
    }
}
