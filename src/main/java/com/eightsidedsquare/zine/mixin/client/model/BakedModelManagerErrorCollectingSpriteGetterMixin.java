package com.eightsidedsquare.zine.mixin.client.model;

import net.minecraft.client.render.model.ErrorCollectingSpriteGetter;
import net.minecraft.client.render.model.SpriteAtlasManager;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(targets = "net/minecraft/client/render/model/BakedModelManager$1")
public abstract class BakedModelManagerErrorCollectingSpriteGetterMixin implements ErrorCollectingSpriteGetter {

    @Shadow @Final private Sprite missingSprite;

    @Shadow @Final
    Map<Identifier, SpriteAtlasManager.AtlasPreparation> field_55477;

    @Override
    public Sprite zine$get(SpriteIdentifier id) {
        SpriteAtlasManager.AtlasPreparation atlas = this.field_55477.get(id.getAtlasId());
        Sprite sprite = atlas.getSprite(id.getTextureId());
        return sprite == null ? atlas.getMissingSprite() : sprite;
    }

    @Override
    public Sprite zine$getMissing() {
        return this.missingSprite;
    }
}
