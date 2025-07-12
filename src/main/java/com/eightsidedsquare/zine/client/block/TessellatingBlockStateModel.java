package com.eightsidedsquare.zine.client.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.client.model.loading.v1.CustomUnbakedBlockStateModel;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableMesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.BlockModelPart;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class TessellatingBlockStateModel implements BlockStateModel {

    private final Mesh[] meshes;
    private final Sprite particleSprite;
    private final int size;

    private TessellatingBlockStateModel(Mesh[] meshes, Sprite particleSprite, int size) {
        this.meshes = meshes;
        this.particleSprite = particleSprite;
        this.size = size;
    }

    @Override
    public void emitQuads(QuadEmitter emitter, BlockRenderView blockView, BlockPos pos, BlockState state, Random random, Predicate<@Nullable Direction> cullTest) {
        for(Direction.Axis axis : Direction.Axis.values()) {
            int x = Math.floorMod(axis.choose(-1 - pos.getZ(), pos.getX(), pos.getX()), this.size);
            int y = Math.floorMod(axis.choose(pos.getY(), pos.getZ(), pos.getY()), this.size);
            this.meshes[getIndex(x, y, this.size, axis)].outputTo(emitter);
        }
    }

    @Override
    public void addParts(Random random, List<BlockModelPart> parts) {

    }

    @Override
    public Sprite particleSprite() {
        return this.particleSprite;
    }

    private static int getIndex(int x, int y, int size, Direction.Axis axis) {
        return (x + y * size) * 3 + axis.ordinal();
    }

    public record Unbaked(Identifier texture, Optional<Identifier> particleTexture, int size) implements CustomMeshUnbakedBlockStateModel {

        public static final MapCodec<Unbaked> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Identifier.CODEC.fieldOf("texture").forGetter(Unbaked::texture),
                Identifier.CODEC.optionalFieldOf("particle_texture").forGetter(Unbaked::particleTexture),
                Codecs.POSITIVE_INT.fieldOf("size").forGetter(Unbaked::size)
        ).apply(instance, Unbaked::new));

        public Unbaked(Identifier texture, Identifier particleTexture, int size) {
            this(texture, Optional.of(particleTexture), size);
        }

        public Unbaked(Identifier texture, int size) {
            this(texture, Optional.empty(), size);
        }

        @Override
        public MapCodec<? extends CustomUnbakedBlockStateModel> codec() {
            return CODEC;
        }

        @Override
        public int getMeshCount() {
            return this.size * this.size * 3;
        }

        @Override
        public BlockStateModel bake(MutableMesh builder, QuadEmitter emitter, Mesh[] meshes, Baker baker) {
            Sprite sprite = baker.zine$getSprite(this.texture);
            float ratio = this.size / (float) (1 << MathHelper.ceilLog2(this.size));
            for (Direction.Axis axis : Direction.Axis.values()) {
                for(int x = 0; x < this.size; x++) {
                    for(int y = 0; y < this.size; y++) {
                        if(axis.isVertical()) {
                            emitFace(emitter, sprite, axis.getPositiveDirection(), x, y, this.size, ratio);
                            emitFace(emitter, sprite, axis.getNegativeDirection(), x, this.size - y - 1, this.size, ratio);
                        }else {
                            emitFace(emitter, sprite, axis.getPositiveDirection(), x, this.size - y - 1, this.size, ratio);
                            emitFace(emitter, sprite, axis.getNegativeDirection(), this.size - x - 1, this.size - y - 1, this.size, ratio);
                        }
                        meshes[getIndex(x, y, this.size, axis)] = builder.immutableCopy();
                        builder.clear();
                    }
                }
            }
            Sprite particleSprite = this.particleTexture.map(baker::zine$getSprite).orElse(sprite);
            return new TessellatingBlockStateModel(meshes, particleSprite, this.size);
        }

        private static void emitFace(QuadEmitter emitter, Sprite sprite, Direction direction, int x, int y, int size, float ratio) {
            emitter.square(direction, 0, 0, 1, 1, 0);
            float u1 = (x / (float) size) * ratio;
            float u2 = ((x + 1) / (float) size) * ratio;
            float v1 = (y / (float) size) * ratio;
            float v2 = ((y + 1) / (float) size) * ratio;
            emitter.uv(0, u1, v1);
            emitter.uv(1, u1, v2);
            emitter.uv(2, u2, v2);
            emitter.uv(3, u2, v1);
            emitter.spriteBake(sprite, MutableQuadView.BAKE_NORMALIZED);
            emitter.color(-1, -1, -1, -1);
            emitter.emit();
        }
    }
}
