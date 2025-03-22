package com.eightsidedsquare.zine.client.atlas.gradient;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;

public record FlatGradient(int color) implements Gradient {

    public static final MapCodec<FlatGradient> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codecs.RGB.fieldOf("argb").forGetter(FlatGradient::color)
    ).apply(instance, FlatGradient::new));

    @Override
    public MapCodec<? extends Gradient> getCodec() {
        return CODEC;
    }

    @Override
    public int get(float u, float v, float w) {
        return this.color;
    }
}
