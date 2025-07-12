package com.eightsidedsquare.zine.client.model;

import com.eightsidedsquare.zine.client.util.SpriteIds;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

public interface ZineErrorCollectingSpriteGetter {

    default Sprite zine$get(SpriteIdentifier id) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Sprite zine$get(Identifier id) {
        return this.zine$get(SpriteIds.block(id));
    }

    default Sprite zine$getMissing() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
