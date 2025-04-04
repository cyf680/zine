package com.eightsidedsquare.zine.client.block.model;

import com.eightsidedsquare.zine.client.util.ConnectedPattern;
import com.eightsidedsquare.zine.client.util.ConnectedShape;
import com.eightsidedsquare.zine.client.util.SpriteIds;
import com.eightsidedsquare.zine.common.util.Offset;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableMesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ConnectedBlockModel implements BlockStateModel, BlockStateModel.Unbaked, SimpleModel {

    protected static final Identifier PARENT_ID = Identifier.ofVanilla("block/block");
    protected static final Offset[] X_OFFSETS = {
            Offset.UP_SOUTH, Offset.UP, Offset.UP_NORTH, Offset.NORTH, Offset.DOWN_NORTH, Offset.DOWN, Offset.DOWN_SOUTH, Offset.SOUTH
    };
    protected static final Offset[] Y_OFFSETS = {
            Offset.NORTHWEST, Offset.NORTH, Offset.NORTHEAST, Offset.EAST, Offset.SOUTHEAST, Offset.SOUTH, Offset.SOUTHWEST, Offset.WEST
    };
    protected static final Offset[] Z_OFFSETS = {
            Offset.UP_WEST, Offset.UP, Offset.UP_EAST, Offset.EAST, Offset.DOWN_EAST, Offset.DOWN, Offset.DOWN_WEST, Offset.WEST
    };
    protected final EnumMap<ConnectedPattern, SpriteIdentifier> spriteIds = new EnumMap<>(ConnectedPattern.class);
    protected final EnumMap<ConnectedPattern, Mesh[]> meshes = new EnumMap<>(ConnectedPattern.class);
    private final Identifier baseName;
    protected Sprite particleSprite;

    public ConnectedBlockModel(Identifier baseName,
                               Identifier allTexture,
                               Identifier cornersTexture,
                               Identifier horizontalTexture,
                               Identifier noneTexture,
                               Identifier verticalTexture) {
        this.baseName = baseName;
        for(ConnectedPattern pattern : ConnectedPattern.values()) {
            if(pattern.allMatch()) {
                this.spriteIds.put(pattern, SpriteIds.block(switch (pattern.getNW()) {
                    case ALL -> allTexture;
                    case CORNER -> cornersTexture;
                    case HORIZONTAL -> horizontalTexture;
                    case NONE -> noneTexture;
                    case VERTICAL -> verticalTexture;
                }));
            }else {
                this.spriteIds.put(pattern, SpriteIds.block(pattern.addSuffix(baseName)));
            }
        }
    }

    public ConnectedBlockModel(Identifier baseName) {
        this(
                baseName,
                baseName.withSuffixedPath("_all"),
                baseName.withSuffixedPath("_corners"),
                baseName.withSuffixedPath("_horizontal"),
                baseName.withSuffixedPath("_none"),
                baseName.withSuffixedPath("_vertical")
        );
    }

    protected int getFlags(Predicate<BlockState> predicate, BlockView world, BlockPos pos) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        int flags = 0;
        for(Offset offset : Offset.values(this.getOffsetDistance())) {
            if(predicate.test(world.getBlockState(mutable.set(pos, offset.getX(), offset.getY(), offset.getZ())))) {
                flags |= 1 << offset.ordinal();
            }
        }
        return flags;
    }

    protected int getOffsetDistance() {
        return 2;
    }

    protected boolean getFlag(int flags, Offset offset) {
        return (flags & (1 << offset.ordinal())) != 0;
    }

    protected ConnectedShape getShape(Offset[] offsets, int flags, int vertical, int horizontal, int corner) {
        if(this.getFlag(flags, offsets[vertical])) {
            if(this.getFlag(flags, offsets[horizontal])) {
                if(this.getFlag(flags, offsets[corner])) {
                    return ConnectedShape.NONE;
                }
                return ConnectedShape.CORNER;
            }
            return ConnectedShape.VERTICAL;
        }else if(this.getFlag(flags, offsets[horizontal])) {
            return ConnectedShape.HORIZONTAL;
        }
        return ConnectedShape.ALL;
    }

    protected ConnectedPattern getPattern(Direction.Axis axis, int flags) {
        Offset[] offsets = switch (axis) {
            case X -> X_OFFSETS;
            case Y -> Y_OFFSETS;
            case Z -> Z_OFFSETS;
        };
        return ConnectedPattern.from(
                this.getShape(offsets, flags, 1, 7, 0),
                this.getShape(offsets, flags, 1, 3, 2),
                this.getShape(offsets, flags, 5, 3, 4),
                this.getShape(offsets, flags, 5, 7, 6)
        );
    }
    @Override
    public void emitQuads(QuadEmitter emitter, BlockRenderView world, BlockPos pos, BlockState state, Random random, Predicate<@Nullable Direction> cullTest) {
        Block block = state.getBlock();
        int flags = this.getFlags(blockState -> blockState.isOf(block), world, pos);
        for(Direction.Axis axis : Direction.Axis.values()) {
            this.meshes.get(this.getPattern(axis, flags))[axis.ordinal()].outputTo(emitter);
        }
    }

    @Override
    public BlockStateModel bake(Baker baker) {
        this.meshes.clear();
        ErrorCollectingSpriteGetter spriteGetter = baker.getSpriteGetter();
        Renderer renderer = Renderer.get();
        MutableMesh builder = renderer.mutableMesh();
        QuadEmitter emitter = builder.emitter();

        EnumMap<ConnectedPattern, Sprite> sprites = new EnumMap<>(ConnectedPattern.class);

        for(Map.Entry<ConnectedPattern, SpriteIdentifier> entry : this.spriteIds.entrySet()) {
            sprites.put(entry.getKey(), spriteGetter.get(entry.getValue(), this));
        }

        for(ConnectedPattern pattern : ConnectedPattern.values()) {
            Sprite frontSprite = sprites.get(pattern);
            Sprite backSideSprite = sprites.get(pattern.flipHorizontal());
            Sprite backBottomSprite = sprites.get(pattern.flipVertical());
            this.emitMeshes(builder, emitter, pattern, frontSprite, backBottomSprite, backSideSprite);
        }
        builder.clear();
        this.particleSprite = sprites.get(ConnectedPattern.AAAA);
        return this;
    }

    protected void emitMeshes(MutableMesh builder, QuadEmitter emitter, ConnectedPattern pattern, Sprite frontSprite, Sprite backBottomSprite, Sprite backSideSprite) {
        for(Direction.Axis axis : Direction.Axis.values()) {
            builder.clear();
            this.emitMeshForAxis(axis, frontSprite, axis == Direction.Axis.Y ? backBottomSprite : backSideSprite, emitter);
            this.meshes.computeIfAbsent(pattern, p -> new Mesh[3])[axis.ordinal()] = builder.immutableCopy();
        }
    }

    protected void emitMeshForAxis(Direction.Axis axis, Sprite frontSprite, Sprite backSprite, QuadEmitter emitter) {
        this.emitMeshForDirection(axis.getPositiveDirection(), frontSprite, emitter);
        this.emitMeshForDirection(axis.getNegativeDirection(), backSprite, emitter);
    }

    protected void emitMeshForDirection(Direction direction, Sprite sprite, QuadEmitter emitter) {
        emitter.square(direction, 0, 0, 1, 1, 0);
        emitter.spriteBake(sprite, MutableQuadView.BAKE_LOCK_UV);
        emitter.color(-1, -1, -1, -1);
        emitter.emit();
    }

    @Override
    public void resolve(Resolver resolver) {
        resolver.markDependency(PARENT_ID);
    }

    @Override
    public String name() {
        return this.baseName.toString();
    }

    @Override
    public void addParts(Random random, List<BlockModelPart> parts) {

    }

    @Override
    public Sprite particleSprite() {
        return this.particleSprite;
    }
}
