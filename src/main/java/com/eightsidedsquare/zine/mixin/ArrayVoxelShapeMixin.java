package com.eightsidedsquare.zine.mixin;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.util.shape.ArrayVoxelShape;
import net.minecraft.util.shape.VoxelSet;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

@SuppressWarnings("SuspiciousNameCombination")
@Mixin(ArrayVoxelShape.class)
public abstract class ArrayVoxelShapeMixin extends VoxelShapeMixin {

    @Shadow @Final private DoubleList xPoints;
    @Shadow @Final private DoubleList yPoints;
    @Shadow @Final private DoubleList zPoints;

    @Invoker("<init>")
    private static ArrayVoxelShape zine$init(VoxelSet shape, DoubleList xPoints, DoubleList yPoints, DoubleList zPoints) {
        throw new AssertionError();
    }

    @Unique
    private DoubleArrayList zine$flip(DoubleList pts) {
        double[] ptsArr = new double[pts.size()];
        for(int i = 0; i < ptsArr.length; i++) {
            ptsArr[ptsArr.length - i - 1] = 1 - pts.getDouble(i);
        }
        return DoubleArrayList.wrap(ptsArr);
    }

    @Unique
    private DoubleArrayList zine$flipX() {
        return this.zine$flip(this.xPoints);
    }

    @Unique
    private DoubleArrayList zine$flipY() {
        return this.zine$flip(this.yPoints);
    }

    @Unique
    private DoubleArrayList zine$flipZ() {
        return this.zine$flip(this.zPoints);
    }

    @Override
    public VoxelShape zine$rotateXClockwise() {
        return zine$init(
                this.voxels.zine$rotateXClockwise(),
                this.xPoints,
                this.zPoints,
                this.zine$flipY()
        );
    }

    @Override
    public VoxelShape zine$rotateXCounterclockwise() {
        return zine$init(
                this.voxels.zine$rotateXCounterclockwise(),
                this.xPoints,
                this.zine$flipZ(),
                this.yPoints
        );
    }

    @Override
    public VoxelShape zine$rotateXOpposite() {
        return zine$init(
                this.voxels.zine$rotateXOpposite(),
                this.xPoints,
                this.zine$flipY(),
                this.zine$flipZ()
        );
    }

    @Override
    public VoxelShape zine$rotateYClockwise() {
        return zine$init(
                this.voxels.zine$rotateYClockwise(),
                this.zine$flipZ(),
                this.yPoints,
                this.xPoints
        );
    }

    @Override
    public VoxelShape zine$rotateYCounterclockwise() {
        return zine$init(
                this.voxels.zine$rotateYCounterclockwise(),
                this.zPoints,
                this.yPoints,
                this.zine$flipX()
        );
    }

    @Override
    public VoxelShape zine$rotateYOpposite() {
        return zine$init(
                this.voxels.zine$rotateYOpposite(),
                this.zine$flipX(),
                this.yPoints,
                this.zine$flipZ()
        );
    }

    @Override
    public VoxelShape zine$rotateZClockwise() {
        return zine$init(
                this.voxels.zine$rotateZClockwise(),
                this.yPoints,
                this.zine$flipX(),
                this.zPoints
        );
    }

    @Override
    public VoxelShape zine$rotateZCounterclockwise() {
        return zine$init(
                this.voxels.zine$rotateZCounterclockwise(),
                this.zine$flipY(),
                this.xPoints,
                this.zPoints
        );
    }

    @Override
    public VoxelShape zine$rotateZOpposite() {
        return zine$init(
                this.voxels.zine$rotateYOpposite(),
                this.zine$flipX(),
                this.zine$flipY(),
                this.zPoints
        );
    }

    @Override
    public VoxelShape zine$mirrorX() {
        return zine$init(
                this.voxels.zine$mirrorX(),
                this.zine$flipX(),
                this.yPoints,
                this.zPoints
        );
    }

    @Override
    public VoxelShape zine$mirrorY() {
        return zine$init(
                this.voxels.zine$mirrorY(),
                this.xPoints,
                this.zine$flipY(),
                this.zPoints
        );
    }

    @Override
    public VoxelShape zine$mirrorZ() {
        return zine$init(
                this.voxels.zine$mirrorZ(),
                this.xPoints,
                this.yPoints,
                this.zine$flipZ()
        );
    }
}
