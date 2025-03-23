package com.eightsidedsquare.zine.mixin;

import com.mojang.serialization.Encoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "com/mojang/serialization/Codec$2")
public interface SimpleCodecAccessor {

    @Accessor(value = "val$encoder", remap = false)
    Encoder<?> getEncoder();

}
