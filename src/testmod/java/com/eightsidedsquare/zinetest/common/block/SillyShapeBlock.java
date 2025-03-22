package com.eightsidedsquare.zinetest.common.block;

import com.eightsidedsquare.zine.common.state.StateMap;
import com.eightsidedsquare.zine.common.util.shape.VoxelShapeUtil;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.state.StateManager;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class SillyShapeBlock extends WallMountedBlock {

    private static final VoxelShape NORTH_SHAPE = VoxelShapes.combineAndSimplify(
            VoxelShapes.union(
                    Block.createCuboidShape(4, 1, 0, 12, 15, 8),
                    Block.createCuboidShape(0, 7, 1, 13, 13, 3)
            ),
            Block.createCuboidShape(5, 5, 0, 11, 11, 8),
            BooleanBiFunction.ONLY_FIRST
    );
    private static final StateMap<VoxelShape> SHAPES = VoxelShapeUtil.createWallMountedMap(NORTH_SHAPE);

    public SillyShapeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(super.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES.get(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, FACE);
    }

    @Override
    protected MapCodec<? extends WallMountedBlock> getCodec() {
        return null;
    }
}
