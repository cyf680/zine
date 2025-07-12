package com.eightsidedsquare.zine.mixin.client.model;

import com.eightsidedsquare.zine.client.model.ZineErrorCollectingSpriteGetter;
import net.minecraft.client.render.model.ErrorCollectingSpriteGetter;
import net.minecraft.client.render.model.SimpleModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ErrorCollectingSpriteGetter.class)
public interface ErrorCollectingSpriteGetterMixin extends ZineErrorCollectingSpriteGetter {

    @Shadow Sprite get(SpriteIdentifier id, SimpleModel model);

    @Shadow Sprite getMissing(String name, SimpleModel model);

    @Override
    default Sprite zine$get(SpriteIdentifier id) {
        return this.get(id, () -> "Unknown Model");
    }

    @Override
    default Sprite zine$getMissing() {
        return this.getMissing("Unknown Texture", () -> "Unknown Model");
    }
}
