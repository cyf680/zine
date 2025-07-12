package com.eightsidedsquare.zine.common.entity.spawn;

import com.eightsidedsquare.zine.common.util.codec.CodecUtil;
import com.eightsidedsquare.zine.common.world.NoiseRouterNoise;
import com.eightsidedsquare.zine.common.world.density_function.MutableNoisePos;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.spawn.SpawnCondition;
import net.minecraft.entity.spawn.SpawnContext;
import net.minecraft.predicate.NumberRange;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.noise.NoiseRouter;

import java.util.List;

public class NoiseSpawnCondition implements SpawnCondition {

    public static final MapCodec<NoiseSpawnCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            NoiseRouterNoise.CODEC.fieldOf("noise").forGetter(NoiseSpawnCondition::getNoise),
            CodecUtil.nonEmptyListCodec(NumberRange.DoubleRange.CODEC).fieldOf("ranges").forGetter(NoiseSpawnCondition::getRanges)
    ).apply(instance, NoiseSpawnCondition::new));
    private static final MutableNoisePos NOISE_POS = new MutableNoisePos(0, 0, 0);
    private NoiseRouterNoise noise;
    private List<NumberRange.DoubleRange> ranges;

    public NoiseSpawnCondition(NoiseRouterNoise noise, List<NumberRange.DoubleRange> ranges) {
        this.noise = noise;
        this.ranges = ranges;
    }

    @Override
    public MapCodec<? extends SpawnCondition> getCodec() {
        return CODEC;
    }

    public NoiseRouterNoise getNoise() {
        return this.noise;
    }

    public void setNoise(NoiseRouterNoise noise) {
        this.noise = noise;
    }

    public List<NumberRange.DoubleRange> getRanges() {
        return this.ranges;
    }

    public void setRanges(List<NumberRange.DoubleRange> ranges) {
        this.ranges = ranges;
    }

    @Override
    public boolean test(SpawnContext spawnContext) {
        BlockPos pos = spawnContext.pos();
        NOISE_POS.set(pos.getX(), pos.getY(), pos.getZ());
        ServerWorld world = spawnContext.world().toServerWorld();
        NoiseRouter noiseRouter = world.getChunkManager().getNoiseConfig().getNoiseRouter();
        double sample = this.noise.densityFunctionGetter.apply(noiseRouter).sample(NOISE_POS);
        for(NumberRange.DoubleRange range : this.ranges) {
            if(range.test(sample)) {
                return true;
            }
        }
        return false;
    }
}
