package com.eightsidedsquare.zine.mixin.client;

import com.eightsidedsquare.zine.client.atlas.NativeImageExtensions;
import net.minecraft.client.texture.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NativeImage.class)
public abstract class NativeImageMixin implements NativeImageExtensions {

    @Shadow private long pointer;

    @Override
    public long zine$getPointer() {
        return this.pointer;
    }
}
