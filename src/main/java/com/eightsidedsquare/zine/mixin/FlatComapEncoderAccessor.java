package com.eightsidedsquare.zine.mixin;

import com.mojang.serialization.Encoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "com/mojang/serialization/Encoder$2")
public interface FlatComapEncoderAccessor {

    @Accessor(value = "this$0", remap = false)
    Encoder<?> getThis();

}
