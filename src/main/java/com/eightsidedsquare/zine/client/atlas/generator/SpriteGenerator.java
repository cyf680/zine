package com.eightsidedsquare.zine.client.atlas.generator;

import com.eightsidedsquare.zine.client.ZineClient;
import com.eightsidedsquare.zine.client.atlas.GeneratorAtlasSource;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;

import java.util.function.Function;

public interface SpriteGenerator {

    Codecs.IdMapper<Identifier, MapCodec<? extends SpriteGenerator>> ID_MAPPER = new Codecs.IdMapper<>();
    Codec<SpriteGenerator> CODEC = ID_MAPPER.getCodec(Identifier.CODEC).dispatch(SpriteGenerator::getCodec, Function.identity());

    static void bootstrap() {
        ZineClient.REGISTRY.spriteGenerator("gradient", GradientSpriteGenerator.CODEC);
        ZineClient.REGISTRY.spriteGenerator("noise", NoiseSpriteGenerator.CODEC);
    }

    MapCodec<? extends SpriteGenerator> getCodec();

    GeneratorAtlasSource.Output generate(Identifier outputId, SpriteProperties properties);
}
