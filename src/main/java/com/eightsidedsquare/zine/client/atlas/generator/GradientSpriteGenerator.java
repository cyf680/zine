package com.eightsidedsquare.zine.client.atlas.generator;

import com.eightsidedsquare.zine.client.atlas.GeneratorAtlasSource;
import com.eightsidedsquare.zine.client.atlas.gradient.Gradient;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;

public record GradientSpriteGenerator(Gradient gradient) implements SpriteGenerator {

    public <T extends Gradient> GradientSpriteGenerator(Gradient.Builder<T> builder) {
        this(builder.build());
    }

    public static final MapCodec<GradientSpriteGenerator> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Gradient.CODEC.fieldOf("gradient").forGetter(GradientSpriteGenerator::gradient)
    ).apply(instance, GradientSpriteGenerator::new));

    @Override
    public MapCodec<? extends SpriteGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public GeneratorAtlasSource.Output generate(Identifier outputId, SpriteProperties properties) {
        return this.gradient::get;
    }
}
