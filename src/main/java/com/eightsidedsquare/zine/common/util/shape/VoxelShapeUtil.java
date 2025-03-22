package com.eightsidedsquare.zine.common.util.shape;

import com.eightsidedsquare.zine.common.state.StateMap;
import com.eightsidedsquare.zine.common.state.StateMapBuilder;
import net.minecraft.block.enums.Orientation;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public final class VoxelShapeUtil {
    private VoxelShapeUtil() {
    }

    public static final VoxelShape EMPTY = VoxelShapes.empty();

    /**
     * Creates a state map for the direction property with the voxel shape transformed for each value.
     */
    public static StateMap<VoxelShape> createFacingMap(VoxelShape shape, EnumProperty<Direction> property) {
        return StateMapBuilder.create(property, direction -> switch (direction) {
            case EAST -> shape.zine$rotateYClockwise();
            case SOUTH -> shape.zine$rotateYOpposite();
            case WEST -> shape.zine$rotateYCounterclockwise();
            case UP -> shape.zine$rotateXCounterclockwise();
            case DOWN -> shape.zine$rotateXClockwise();
            default -> shape;
        });
    }

    /**
     * Creates a state map for the orientation property with the voxel shape transformed for each value.
     */
    public static StateMap<VoxelShape> createOrientationMap(VoxelShape shape, EnumProperty<Orientation> property) {
        VoxelShape upShape = shape.zine$rotateXCounterclockwise();
        VoxelShape downShape = shape.zine$rotateXClockwise();
        return StateMapBuilder.create(property, orientation -> switch (orientation) {
            case EAST_UP -> shape.zine$rotateYClockwise();
            case SOUTH_UP -> shape.zine$rotateYOpposite();
            case WEST_UP -> shape.zine$rotateYCounterclockwise();
            case UP_SOUTH -> upShape;
            case UP_WEST -> upShape.zine$rotateYClockwise();
            case UP_NORTH -> upShape.zine$rotateYOpposite();
            case UP_EAST -> upShape.zine$rotateYCounterclockwise();
            case DOWN_NORTH -> downShape;
            case DOWN_EAST -> downShape.zine$rotateYClockwise();
            case DOWN_SOUTH -> downShape.zine$rotateYOpposite();
            case DOWN_WEST -> downShape.zine$rotateYCounterclockwise();
            default -> shape;
        });
    }

    /**
     * Creates a state map for {@link net.minecraft.block.WallMountedBlock} properties with the voxel shape transformed for each value.
     */
    public static StateMap<VoxelShape> createWallMountedMap(VoxelShape shape) {
        VoxelShape wallShape = shape.zine$rotateXClockwise();
        VoxelShape ceilingShape = shape.zine$rotateXOpposite();
        return StateMapBuilder.create(Properties.BLOCK_FACE, Properties.HORIZONTAL_FACING, (face, direction) -> {
            VoxelShape faceShape = switch (face) {
                case WALL -> wallShape;
                case CEILING -> ceilingShape;
                default -> shape;
            };
            return switch (direction) {
                case EAST -> faceShape.zine$rotateYClockwise();
                case SOUTH -> faceShape.zine$rotateYOpposite();
                case WEST -> faceShape.zine$rotateYCounterclockwise();
                default -> faceShape;
            };
        });
    }

    /**
     * Creates the state map for {@link net.minecraft.block.WallBlock} properties with the voxel shapes combined for each permutation.
     * @param upShape the voxel shape of the center column
     * @param lowShape the voxel shape of the connecting north side when low
     * @param tallShape the voxel shape of the connecting north side when tall
     */
    public static StateMap<VoxelShape> createWallMap(VoxelShape upShape, VoxelShape lowShape, VoxelShape tallShape) {
        return createWallLikeMap(Properties.UP, upShape, lowShape, tallShape);
    }

    /**
     * Creates the state map for {@link net.minecraft.block.PaleMossCarpetBlock} properties with the voxel shapes combined for each permutation.
     * @param bottomShape the voxel shape of the bottom carpet
     * @param lowShape the voxel shape of the connecting north side when low
     * @param tallShape the voxel shape of the connecting north side when tall
     */
    public static StateMap<VoxelShape> createMossyCarpetMap(VoxelShape bottomShape, VoxelShape lowShape, VoxelShape tallShape) {
        return createWallLikeMap(Properties.BOTTOM, bottomShape, lowShape, tallShape);
    }

    /**
     * Creates the state map for {@link net.minecraft.block.HorizontalConnectingBlock} properties with the voxel shapes combined for each permutation.
     * @param centerShape the voxel shape at the center of each shape
     * @param sideShape the voxel shape of the connecting side facing north
     */
    public static StateMap<VoxelShape> createHorizontalConnectingMap(VoxelShape centerShape, VoxelShape sideShape) {
        VoxelShape eastShape = sideShape.zine$rotateYClockwise();
        VoxelShape southShape = sideShape.zine$rotateYOpposite();
        VoxelShape westShape = sideShape.zine$rotateYCounterclockwise();
        return StateMapBuilder.create(
                Properties.NORTH,
                Properties.EAST,
                Properties.SOUTH,
                Properties.WEST,
                (north, east, south, west) -> VoxelShapes.union(
                        centerShape,
                        north ? sideShape : EMPTY,
                        east ? eastShape : EMPTY,
                        south ? southShape : EMPTY,
                        west ? westShape : EMPTY
                )
        );
    }

    /**
     * Creates the state map for {@link net.minecraft.block.ConnectingBlock} or {@link net.minecraft.block.MultifaceBlock} properties with the voxel shapes combined for each permutation.
     * @param centerShape the voxel shape at the center of each shape
     * @param sideShape the voxel shape of the connecting side facing north
     */
    public static StateMap<VoxelShape> createConnectingMap(VoxelShape centerShape, VoxelShape sideShape) {
        VoxelShape eastShape = sideShape.zine$rotateYClockwise();
        VoxelShape southShape = sideShape.zine$rotateYOpposite();
        VoxelShape westShape = sideShape.zine$rotateYCounterclockwise();
        VoxelShape upShape = sideShape.zine$rotateXCounterclockwise();
        VoxelShape downShape = sideShape.zine$rotateXClockwise();
        return StateMapBuilder.create(
                Properties.NORTH,
                Properties.EAST,
                Properties.SOUTH,
                Properties.WEST,
                Properties.UP,
                Properties.DOWN,
                (north, east, south, west, up, down) -> VoxelShapes.union(
                        centerShape,
                        north ? sideShape : EMPTY,
                        east ? eastShape : EMPTY,
                        south ? southShape : EMPTY,
                        west ? westShape : EMPTY,
                        up ? upShape : EMPTY,
                        down ? downShape : EMPTY
                )
        );
    }

    private static StateMap<VoxelShape> createWallLikeMap(BooleanProperty centerProperty, VoxelShape centerShape, VoxelShape lowShape, VoxelShape tallShape) {
        VoxelShape[] northShapes = {EMPTY, lowShape, tallShape};
        VoxelShape[] eastShapes = {EMPTY, lowShape.zine$rotateYClockwise(), tallShape.zine$rotateYClockwise()};
        VoxelShape[] southShapes = {EMPTY, lowShape.zine$rotateYOpposite(), tallShape.zine$rotateYOpposite()};
        VoxelShape[] westShapes = {EMPTY, lowShape.zine$rotateYCounterclockwise(), tallShape.zine$rotateYCounterclockwise()};
        return StateMapBuilder.create(
                centerProperty,
                Properties.NORTH_WALL_SHAPE,
                Properties.EAST_WALL_SHAPE,
                Properties.SOUTH_WALL_SHAPE,
                Properties.WEST_WALL_SHAPE,
                (center, north, east, south, west) -> VoxelShapes.union(
                        center ? centerShape : EMPTY,
                        northShapes[north.ordinal()],
                        eastShapes[east.ordinal()],
                        southShapes[south.ordinal()],
                        westShapes[west.ordinal()]
                )
        );
    }

}
