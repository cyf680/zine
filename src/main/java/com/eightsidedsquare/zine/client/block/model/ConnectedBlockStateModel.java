package com.eightsidedsquare.zine.client.block.model;

import com.eightsidedsquare.zine.client.util.ConnectedPattern;
import com.eightsidedsquare.zine.client.util.ConnectedShape;
import com.eightsidedsquare.zine.client.util.SpriteIds;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.client.model.loading.v1.CustomUnbakedBlockStateModel;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableMesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Predicate;

public class ConnectedBlockStateModel implements BlockStateModel {

    private final Mesh[] meshes;
    private final Sprite particleSprite;
    private final EnumMap<Direction, ConnectedPatternCalculator> calculators = new EnumMap<>(Direction.class);

    private ConnectedBlockStateModel(Mesh[] meshes, Sprite particleSprite, boolean fancy) {
        this.meshes = meshes;
        this.particleSprite = particleSprite;
        this.calculators.putAll(fancy ? ConnectedPatternCalculator.FANCY_CUBE : ConnectedPatternCalculator.FAST_CUBE);
    }

    @Override
    public void emitQuads(QuadEmitter emitter, BlockRenderView world, BlockPos pos, BlockState state, Random random, Predicate<@Nullable Direction> cullTest) {
        ConnectedShape[] shapes = new ConnectedShape[4];
        for (Direction direction : Direction.values()) {
            if(cullTest.test(direction)) {
                continue;
            }
            ConnectedPattern pattern = this.calculators.get(direction).calculate(shapes, world, pos, state);
            this.meshes[getIndex(pattern, direction)].outputTo(emitter);
        }
    }

    @Override
    public Sprite particleSprite() {
        return this.particleSprite;
    }

    private static int getIndex(ConnectedPattern pattern, Direction direction) {
        return pattern.ordinal() * 6 + direction.ordinal();
    }

    @Override
    public void addParts(Random random, List<BlockModelPart> parts) {

    }

    public record Unbaked(Identifier parent, Identifier baseName, boolean fancy) implements CustomUnbakedBlockStateModel, SimpleModel {

        public static final MapCodec<Unbaked> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Identifier.CODEC.fieldOf("parent").forGetter(Unbaked::parent),
                Identifier.CODEC.fieldOf("base_sprite_name").forGetter(Unbaked::baseName),
                Codec.BOOL.fieldOf("fancy").forGetter(Unbaked::fancy)
        ).apply(instance, Unbaked::new));

        @Override
        public MapCodec<? extends CustomUnbakedBlockStateModel> codec() {
            return CODEC;
        }

        @Override
        public BlockStateModel bake(Baker baker) {
            ErrorCollectingSpriteGetter spriteGetter = baker.getSpriteGetter();
            Renderer renderer = Renderer.get();
            MutableMesh builder = renderer.mutableMesh();
            QuadEmitter emitter = builder.emitter();
            Mesh[] meshes = new Mesh[ConnectedPattern.values().length * 6];
            Sprite particleSprite = null;
            for (ConnectedPattern pattern : ConnectedPattern.values()) {
                Sprite sprite = spriteGetter.get(SpriteIds.block(pattern.addSuffix(this.baseName)), this);
                if(pattern == ConnectedPattern.AAAA) {
                    particleSprite = sprite;
                }
                emitMeshes(meshes, builder, emitter, pattern, sprite);
            }
            builder.clear();
            return new ConnectedBlockStateModel(meshes, particleSprite, fancy);
        }

        private static void emitMeshes(Mesh[] meshes, MutableMesh builder, QuadEmitter emitter, ConnectedPattern pattern, Sprite sprite) {
            for(Direction direction : Direction.values()) {
                builder.clear();
                emitter.square(direction, 0, 0, 1, 1, 0);
                emitter.spriteBake(sprite, MutableQuadView.BAKE_LOCK_UV);
                emitter.color(-1, -1, -1, -1);
                emitter.emit();
                meshes[getIndex(pattern, direction)] = builder.immutableCopy();
            }
        }

        @Override
        public void resolve(Resolver resolver) {
            resolver.markDependency(this.parent);
        }

        @Override
        public String name() {
            return this.toString();
        }
    }

}
