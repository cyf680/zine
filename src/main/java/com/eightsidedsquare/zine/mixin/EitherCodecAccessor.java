package com.eightsidedsquare.zine.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.EitherCodec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EitherCodec.class)
public interface EitherCodecAccessor {

    @Accessor(value = "second", remap = false)
    @Mutable
    void setSecond(Codec<?> codec);

}
