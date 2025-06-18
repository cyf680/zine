package com.eightsidedsquare.zine.client.atlas.generator;

import com.eightsidedsquare.zine.client.atlas.GeneratorAtlasSource;
import com.eightsidedsquare.zine.core.ZineMod;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;

import java.util.function.Function;

public interface SpriteGenerator {

    Codecs.IdMapper<Identifier, MapCodec<? extends SpriteGenerator>> ID_MAPPER = new Codecs.IdMapper<>();
    Codec<SpriteGenerator> CODEC = ID_MAPPER.getCodec(Identifier.CODEC).dispatch(SpriteGenerator::getCodec, Function.identity());

    static void bootstrap() {
        ID_MAPPER.put(ZineMod.id("gradient"), GradientSpriteGenerator.CODEC);
        ID_MAPPER.put(ZineMod.id("noise"), NoiseSpriteGenerator.CODEC);
    }

    MapCodec<? extends SpriteGenerator> getCodec();

    GeneratorAtlasSource.Output generate(Identifier outputId, SpriteProperties properties);
}
