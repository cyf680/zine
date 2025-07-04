package com.eightsidedsquare.zine.client.block.model;

import com.eightsidedsquare.zine.client.util.ConnectedPattern;
import com.eightsidedsquare.zine.client.util.ConnectedShape;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ConnectedPatternCalculator {

    public static final Map<Direction, ConnectedPatternCalculator> FAST_CUBE = ImmutableMap.<Direction, ConnectedPatternCalculator>builder()
            .put(Direction.NORTH, create(Direction.NORTH, Direction.UP, Direction.WEST))
            .put(Direction.EAST, create(Direction.EAST, Direction.UP, Direction.NORTH))
            .put(Direction.SOUTH, create(Direction.SOUTH, Direction.UP, Direction.EAST))
            .put(Direction.WEST, create(Direction.WEST, Direction.UP, Direction.SOUTH))
            .put(Direction.UP, create(Direction.UP, Direction.NORTH, Direction.EAST))
            .put(Direction.DOWN, create(Direction.DOWN, Direction.SOUTH, Direction.EAST))
            .build();
    public static final Map<Direction, ConnectedPatternCalculator> FANCY_CUBE = ImmutableMap.<Direction, ConnectedPatternCalculator>builder()
            .put(Direction.NORTH, createFancy(FAST_CUBE.get(Direction.NORTH), Direction.NORTH, Direction.UP, Direction.WEST))
            .put(Direction.EAST, createFancy(FAST_CUBE.get(Direction.EAST), Direction.EAST, Direction.UP, Direction.NORTH))
            .put(Direction.SOUTH, createFancy(FAST_CUBE.get(Direction.SOUTH), Direction.SOUTH, Direction.UP, Direction.EAST))
            .put(Direction.WEST, createFancy(FAST_CUBE.get(Direction.WEST), Direction.WEST, Direction.UP, Direction.SOUTH))
            .put(Direction.UP, createFancy(FAST_CUBE.get(Direction.UP), Direction.UP, Direction.NORTH, Direction.EAST))
            .put(Direction.DOWN, createFancy(FAST_CUBE.get(Direction.DOWN), Direction.DOWN, Direction.SOUTH, Direction.EAST))
            .build();

    private final Instruction[] instructions;

    private ConnectedPatternCalculator(Instruction[] instructions) {
        this.instructions = instructions;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(ConnectedPatternCalculator calculator) {
        return new Builder(calculator);
    }

    public static InstructionBuilder instruction() {
        return new InstructionBuilder();
    }

    public ConnectedPattern calculate(ConnectedShape[] shapes, BlockRenderView world, BlockPos pos, BlockState state) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        Arrays.fill(shapes, ConnectedShape.NONE);
        for (Instruction instruction : this.instructions) {
            instruction.apply(shapes, pos, state, mutable, world);
        }
        return ConnectedPattern.from(shapes[0], shapes[1], shapes[2], shapes[3]);
    }

    public static ConnectedPatternCalculator create(Direction face, Direction up, Direction right, @Nullable Predicate<BlockState> statePredicate) {
        Direction down = up.getOpposite();
        Direction left = right.getOpposite();
        BlockPos.Mutable offset = new BlockPos.Mutable().move(up).move(right);
        Builder builder = builder();
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 1, 2 -> offset.move(left);
                case 3, 4 -> offset.move(down);
                case 5, 6 -> offset.move(right);
                case 7 -> offset.move(up);
            }
            InstructionBuilder instruction = instruction().face(face).offset(offset).predicate(statePredicate).inverted();
            switch (i) {
                case 0 -> instruction.ne(ConnectedShape.Operation.OR_CORNER);
                case 1 -> instruction.nw(ConnectedShape.Operation.OR_HORIZONTAL).ne(ConnectedShape.Operation.OR_HORIZONTAL);
                case 2 -> instruction.nw(ConnectedShape.Operation.OR_CORNER);
                case 3 -> instruction.nw(ConnectedShape.Operation.OR_VERTICAL).sw(ConnectedShape.Operation.OR_VERTICAL);
                case 4 -> instruction.sw(ConnectedShape.Operation.OR_CORNER);
                case 5 -> instruction.sw(ConnectedShape.Operation.OR_HORIZONTAL).se(ConnectedShape.Operation.OR_HORIZONTAL);
                case 6 -> instruction.se(ConnectedShape.Operation.OR_CORNER);
                case 7 -> instruction.ne(ConnectedShape.Operation.OR_VERTICAL).se(ConnectedShape.Operation.OR_VERTICAL);
            }
            builder.then(instruction);
        }
        return builder.build();
    }

    public static ConnectedPatternCalculator create(Direction face, Direction up, Direction right) {
        return create(face, up, right, null);
    }

    public static ConnectedPatternCalculator createFancy(ConnectedPatternCalculator base, Direction face, Direction up, Direction right, @Nullable Predicate<BlockState> statePredicate) {
        Direction down = up.getOpposite();
        Direction left = right.getOpposite();
        BlockPos.Mutable offset = new BlockPos.Mutable().move(up).move(right).move(face);
        Builder builder = builder(base);
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 1, 2 -> offset.move(left);
                case 3, 4 -> offset.move(down);
                case 5, 6 -> offset.move(right);
                case 7 -> offset.move(up);
            }
            InstructionBuilder instruction = instruction().offset(offset).predicate(statePredicate);
            switch (i) {
                case 0 -> instruction.ne(ConnectedShape.Operation.OR_CORNER).face(left, down);
                case 1 -> instruction.nw(ConnectedShape.Operation.OR_HORIZONTAL).ne(ConnectedShape.Operation.OR_HORIZONTAL).face(down);
                case 2 -> instruction.nw(ConnectedShape.Operation.OR_CORNER).face(right, down);
                case 3 -> instruction.nw(ConnectedShape.Operation.OR_VERTICAL).sw(ConnectedShape.Operation.OR_VERTICAL).face(right);
                case 4 -> instruction.sw(ConnectedShape.Operation.OR_CORNER).face(right, up);
                case 5 -> instruction.sw(ConnectedShape.Operation.OR_HORIZONTAL).se(ConnectedShape.Operation.OR_HORIZONTAL).face(up);
                case 6 -> instruction.se(ConnectedShape.Operation.OR_CORNER).face(left, up);
                case 7 -> instruction.ne(ConnectedShape.Operation.OR_VERTICAL).se(ConnectedShape.Operation.OR_VERTICAL).face(left);
            }
            builder.then(instruction);
        }
        return builder.build();
    }

    public static ConnectedPatternCalculator createFancy(ConnectedPatternCalculator base, Direction face, Direction up, Direction right) {
        return createFancy(base, face, up, right, null);
    }

    interface Instruction {
        void apply(ConnectedShape[] shapes, BlockPos origin, BlockState state, BlockPos.Mutable mutable, BlockRenderView world);
    }

    interface InstructionPredicate {
        boolean test(BlockPos origin, BlockState state, BlockPos.Mutable mutable, BlockRenderView world);
    }

    public static class Builder {
        private final List<Instruction> instructions = new ArrayList<>();

        private Builder() {
        }

        private Builder(ConnectedPatternCalculator calculator) {
            this.instructions.addAll(Arrays.asList(calculator.instructions));
        }

        public Builder then(InstructionBuilder instructionBuilder) {
            this.instructions.add(instructionBuilder.build());
            return this;
        }

        public ConnectedPatternCalculator build() {
            return new ConnectedPatternCalculator(this.instructions.toArray(new Instruction[0]));
        }
    }

    public static class InstructionBuilder {
        @Nullable
        private Predicate<BlockState> statePredicate;
        private Direction[] faces = new Direction[0];
        private int x;
        private int y;
        private int z;
        private ConnectedShape.Operation nwOp = ConnectedShape.Operation.AND_ALL;
        private ConnectedShape.Operation neOp = ConnectedShape.Operation.AND_ALL;
        private ConnectedShape.Operation seOp = ConnectedShape.Operation.AND_ALL;
        private ConnectedShape.Operation swOp = ConnectedShape.Operation.AND_ALL;
        private boolean inverted;

        private InstructionBuilder() {
        }

        public InstructionBuilder predicate(@Nullable Predicate<BlockState> statePredicate) {
            this.statePredicate = statePredicate;
            return this;
        }

        public InstructionBuilder face(Direction... faces) {
            this.faces = faces;
            return this;
        }

        public InstructionBuilder offset(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

        public InstructionBuilder offset(Vec3i offset) {
            return this.offset(offset.getX(), offset.getY(), offset.getZ());
        }

        public InstructionBuilder nw(ConnectedShape.Operation op) {
            this.nwOp = op;
            return this;
        }

        public InstructionBuilder ne(ConnectedShape.Operation op) {
            this.neOp = op;
            return this;
        }

        public InstructionBuilder se(ConnectedShape.Operation op) {
            this.seOp = op;
            return this;
        }

        public InstructionBuilder sw(ConnectedShape.Operation op) {
            this.swOp = op;
            return this;
        }

        public InstructionBuilder inverted() {
            this.inverted = true;
            return this;
        }

        private Instruction build() {
            InstructionPredicate predicate = getInstructionPredicate();
            final int x = this.x;
            final int y = this.y;
            final int z = this.z;
            final ConnectedShape.Operation nwOp = this.nwOp;
            final ConnectedShape.Operation neOp = this.neOp;
            final ConnectedShape.Operation seOp = this.seOp;
            final ConnectedShape.Operation swOp = this.swOp;
            final boolean inverted = this.inverted;
            return (shapes, origin, state, mutable, world) -> {
                if(predicate.test(origin, state, mutable.set(origin, x, y, z), world) != inverted) {
                    shapes[0] = nwOp.apply(shapes[0]);
                    shapes[1] = neOp.apply(shapes[1]);
                    shapes[2] = seOp.apply(shapes[2]);
                    shapes[3] = swOp.apply(shapes[3]);
                }
            };
        }

        private InstructionPredicate getInstructionPredicate() {
            final Predicate<BlockState> statePredicate = this.statePredicate;
            if(this.faces.length == 1) {
                Direction face = this.faces[0];
                return (origin, state, mutable, world) -> {
                    BlockState appearanceState = world.getBlockState(mutable).getAppearance(world, mutable, face, state, origin);
                    return appearanceState.isOf(state.getBlock()) && (statePredicate == null || statePredicate.test(appearanceState));
                };
            }
            final Direction[] faces = this.faces;
            return (origin, state, mutable, world) -> {
                for (Direction face : faces) {
                    BlockState appearanceState = world.getBlockState(mutable).getAppearance(world, mutable, face, state, origin);
                    if(appearanceState.isOf(state.getBlock()) && (statePredicate == null || statePredicate.test(appearanceState))) {
                        return true;
                    }
                }
                return false;
            };
        }

    }
}
