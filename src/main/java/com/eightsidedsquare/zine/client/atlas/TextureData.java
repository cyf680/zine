package com.eightsidedsquare.zine.client.atlas;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.texture.SpriteDimensions;

@Environment(EnvType.CLIENT)
public record TextureData(int[] data, int width, int height) {

    public SpriteDimensions getDimensions() {
        return new SpriteDimensions(this.width, this.height);
    }

}
