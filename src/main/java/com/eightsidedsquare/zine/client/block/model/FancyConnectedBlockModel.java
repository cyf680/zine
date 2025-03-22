package com.eightsidedsquare.zine.client.block.model;

import com.eightsidedsquare.zine.client.util.ConnectedPattern;
import com.eightsidedsquare.zine.client.util.ConnectedShape;
import com.eightsidedsquare.zine.common.util.Offset;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableMesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class FancyConnectedBlockModel extends ConnectedBlockModel {

    protected static final Offset[] NORTH_OFFSETS = {
            Offset.UP_EAST, Offset.UP, Offset.UP_WEST, Offset.WEST, Offset.DOWN_WEST, Offset.DOWN, Offset.DOWN_EAST, Offset.EAST,
            Offset.UP_NORTHEAST, Offset.UP_NORTH, Offset.UP_NORTHWEST, Offset.NORTHWEST, Offset.DOWN_NORTHWEST, Offset.DOWN_NORTH, Offset.DOWN_NORTHEAST, Offset.NORTHEAST
    };
    protected static final Offset[] EAST_OFFSETS = {
            Offset.UP_SOUTH, Offset.UP, Offset.UP_NORTH, Offset.NORTH, Offset.DOWN_NORTH, Offset.DOWN, Offset.DOWN_SOUTH, Offset.SOUTH,
            Offset.UP_SOUTHEAST, Offset.UP_EAST, Offset.UP_NORTHEAST, Offset.NORTHEAST, Offset.DOWN_NORTHEAST, Offset.DOWN_EAST, Offset.DOWN_SOUTHEAST, Offset.SOUTHEAST,
    };
    protected static final Offset[] SOUTH_OFFSETS = {
            Offset.UP_WEST, Offset.UP, Offset.UP_EAST, Offset.EAST, Offset.DOWN_EAST, Offset.DOWN, Offset.DOWN_WEST, Offset.WEST,
            Offset.UP_SOUTHWEST, Offset.UP_SOUTH, Offset.UP_SOUTHEAST, Offset.SOUTHEAST, Offset.DOWN_SOUTHEAST, Offset.DOWN_SOUTH, Offset.DOWN_SOUTHWEST, Offset.SOUTHWEST,
    };
    protected static final Offset[] WEST_OFFSETS = {
            Offset.UP_NORTH, Offset.UP, Offset.UP_SOUTH, Offset.SOUTH, Offset.DOWN_SOUTH, Offset.DOWN, Offset.DOWN_NORTH, Offset.NORTH,
            Offset.UP_NORTHWEST, Offset.UP_WEST, Offset.UP_SOUTHWEST, Offset.SOUTHWEST, Offset.DOWN_SOUTHWEST, Offset.DOWN_WEST, Offset.DOWN_NORTHWEST, Offset.NORTHWEST
    };
    protected static final Offset[] UP_OFFSETS = {
            Offset.NORTHWEST, Offset.NORTH, Offset.NORTHEAST, Offset.EAST, Offset.SOUTHEAST, Offset.SOUTH, Offset.SOUTHWEST, Offset.WEST,
            Offset.UP_NORTHWEST, Offset.UP_NORTH, Offset.UP_NORTHEAST, Offset.UP_EAST, Offset.UP_SOUTHEAST, Offset.UP_SOUTH, Offset.UP_SOUTHWEST, Offset.UP_WEST,
    };
    protected static final Offset[] DOWN_OFFSETS = {
            Offset.SOUTHWEST, Offset.SOUTH, Offset.SOUTHEAST, Offset.EAST, Offset.NORTHEAST, Offset.NORTH, Offset.NORTHWEST, Offset.WEST,
            Offset.DOWN_SOUTHWEST, Offset.DOWN_SOUTH, Offset.DOWN_SOUTHEAST, Offset.DOWN_EAST, Offset.DOWN_NORTHEAST, Offset.DOWN_NORTH, Offset.DOWN_NORTHWEST, Offset.DOWN_WEST,
    };

    public FancyConnectedBlockModel(Identifier baseName,
                                    Identifier allTexture,
                                    Identifier cornersTexture,
                                    Identifier horizontalTexture,
                                    Identifier noneTexture,
                                    Identifier verticalTexture) {
        super(baseName, allTexture, cornersTexture, horizontalTexture, noneTexture, verticalTexture);
    }

    public FancyConnectedBlockModel(Identifier baseName) {
        super(baseName);
    }

    @Override
    public void emitBlockQuads(QuadEmitter emitter, BlockRenderView world, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, Predicate<@Nullable Direction> cullTest) {
        Block block = state.getBlock();
        int flags = this.getFlags(blockState -> blockState.isOf(block), world, pos);
        for(Direction direction : Direction.values()) {
            this.meshes.get(this.getPattern(direction, flags))[direction.ordinal()].outputTo(emitter);
        }
    }

    @Override
    protected ConnectedShape getShape(Offset[] offsets, int flags, int vertical, int horizontal, int corner) {
        if(this.getFlag(flags, offsets[vertical]) && !this.getFlag(flags, offsets[vertical + 8])) {
            if(this.getFlag(flags, offsets[horizontal]) && !this.getFlag(flags, offsets[horizontal + 8])) {
                if(this.getFlag(flags, offsets[corner]) && !this.getFlag(flags, offsets[corner + 8])) {
                    return ConnectedShape.NONE;
                }
                return ConnectedShape.CORNER;
            }
            return ConnectedShape.VERTICAL;
        }else if(this.getFlag(flags, offsets[horizontal]) && !this.getFlag(flags, offsets[horizontal + 8])) {
            return ConnectedShape.HORIZONTAL;
        }
        return ConnectedShape.ALL;
    }

    protected ConnectedPattern getPattern(Direction direction, int flags) {
        Offset[] offsets = switch (direction) {
            case NORTH -> NORTH_OFFSETS;
            case EAST -> EAST_OFFSETS;
            case SOUTH -> SOUTH_OFFSETS;
            case WEST -> WEST_OFFSETS;
            case UP -> UP_OFFSETS;
            case DOWN -> DOWN_OFFSETS;
        };
        return ConnectedPattern.from(
                this.getShape(offsets, flags, 1, 7, 0),
                this.getShape(offsets, flags, 1, 3, 2),
                this.getShape(offsets, flags, 5, 3, 4),
                this.getShape(offsets, flags, 5, 7, 6)
        );
    }

    @Override
    protected int getOffsetDistance() {
        return 3;
    }

    @Override
    protected void emitMeshes(MutableMesh builder, QuadEmitter emitter, ConnectedPattern pattern, Sprite frontSprite, Sprite backBottomSprite, Sprite backSideSprite) {
        for(Direction direction : Direction.values()) {
            builder.clear();
            this.emitMeshForDirection(direction, frontSprite, emitter);
            this.meshes.computeIfAbsent(pattern, p -> new Mesh[6])[direction.ordinal()] = builder.immutableCopy();
        }
    }
}
