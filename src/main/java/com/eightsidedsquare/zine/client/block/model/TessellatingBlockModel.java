package com.eightsidedsquare.zine.client.block.model;

import com.eightsidedsquare.zine.client.util.SpriteIds;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableMesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Predicate;

public class TessellatingBlockModel implements BlockStateModel, BlockStateModel.Unbaked, SimpleModel {

    protected static final Identifier PARENT_ID = Identifier.ofVanilla("block/block");
    protected final EnumMap<Direction.Axis, Mesh[]> meshes = new EnumMap<>(Direction.Axis.class);
    protected final SpriteIdentifier spriteId;
    protected final SpriteIdentifier particleSpriteId;
    protected final Identifier name;
    protected final int size;
    protected Sprite particleSprite;

    public TessellatingBlockModel(Identifier id, Identifier particleId, Identifier name, int size) {
        this.spriteId = SpriteIds.block(id);
        this.particleSpriteId = SpriteIds.block(particleId);
        this.name = name;
        this.size = size;
    }

    public TessellatingBlockModel(Identifier id, int size) {
        this(id, id, id, size);
    }

    @Override
    public void emitQuads(QuadEmitter emitter, BlockRenderView blockView, BlockPos pos, BlockState state, Random random, Predicate<@Nullable Direction> cullTest) {
        for(Direction.Axis axis : Direction.Axis.values()) {
            int x = Math.floorMod(axis.choose(-1 - pos.getZ(), pos.getX(), pos.getX()), this.size);
            int y = Math.floorMod(axis.choose(pos.getY(), pos.getZ(), pos.getY()), this.size);
            this.meshes.get(axis)[x + y * this.size].outputTo(emitter);
        }
    }

    @Override
    public void addParts(Random random, List<BlockModelPart> parts) {

    }

    @Override
    public Sprite particleSprite() {
        return this.particleSprite;
    }

    @Override
    public BlockStateModel bake(Baker baker) {
        this.meshes.clear();
        int area = this.size * this.size;
        ErrorCollectingSpriteGetter spriteGetter = baker.getSpriteGetter();
        Sprite sprite = spriteGetter.get(this.spriteId, this);
        this.particleSprite = spriteGetter.get(this.particleSpriteId, this);
        Renderer renderer = Renderer.get();
        MutableMesh builder = renderer.mutableMesh();
        QuadEmitter emitter = builder.emitter();

        for(Direction.Axis axis : Direction.Axis.values()) {
            Mesh[] directionMeshes = this.meshes.computeIfAbsent(axis, a -> new Mesh[area]);
            for(int x = 0; x < this.size; x++) {
                for(int y = 0; y < this.size; y++) {
                    if(axis.isVertical()) {
                        this.emitFace(emitter, sprite, axis.getPositiveDirection(), x, y);
                        this.emitFace(emitter, sprite, axis.getNegativeDirection(), x, this.size - y - 1);
                    }else {
                        this.emitFace(emitter, sprite, axis.getPositiveDirection(), x, this.size - y - 1);
                        this.emitFace(emitter, sprite, axis.getNegativeDirection(), this.size - x - 1, this.size - y - 1);
                    }
                    directionMeshes[x + y * this.size] = builder.immutableCopy();
                    builder.clear();
                }
            }
        }
        return this;
    }

    protected void emitFace(QuadEmitter emitter, Sprite sprite, Direction direction, int x, int y) {
        emitter.square(direction, 0, 0, 1, 1, 0);
        float u1 = x / (float) this.size;
        float u2 = (x + 1) / (float) this.size;
        float v1 = y / (float) this.size;
        float v2 = (y + 1) / (float) this.size;
        emitter.uv(0, u1, v1);
        emitter.uv(1, u1, v2);
        emitter.uv(2, u2, v2);
        emitter.uv(3, u2, v1);
        emitter.spriteBake(sprite, MutableQuadView.BAKE_NORMALIZED);
        emitter.color(-1, -1, -1, -1);
        emitter.emit();
    }

    @Override
    public void resolve(Resolver resolver) {
        resolver.markDependency(PARENT_ID);
    }

    @Override
    public String name() {
        return "";
    }
}
