package com.eightsidedsquare.zine.client.atlas.gradient;

import com.eightsidedsquare.zine.common.util.codec.CodecUtil;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.util.dynamic.Codecs;

import java.util.List;

public record DiscreteGradient(List<Integer> colors) implements Gradient {

    public static final MapCodec<DiscreteGradient> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            CodecUtil.nonEmptyListCodec(Codecs.RGB).fieldOf("colors").forGetter(DiscreteGradient::colors)
    ).apply(instance, DiscreteGradient::new));

    public DiscreteGradient(int... colors) {
        this(IntList.of(colors));
    }

    @Override
    public MapCodec<? extends Gradient> getCodec() {
        return CODEC;
    }

    @Override
    public int get(float u, float v, float w) {
        if(u >= 1) {
            return this.colors.getLast();
        }else if(u <= 0) {
            return this.colors.getFirst();
        }
        return this.colors.get((int) (this.colors.size() * u));
    }
}
