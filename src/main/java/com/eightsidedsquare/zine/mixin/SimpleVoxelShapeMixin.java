package com.eightsidedsquare.zine.mixin;

import net.minecraft.util.shape.SimpleVoxelShape;
import net.minecraft.util.shape.VoxelSet;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SimpleVoxelShape.class)
public abstract class SimpleVoxelShapeMixin extends VoxelShapeMixin {

    @Invoker(value = "<init>")
    private static SimpleVoxelShape zine$init(VoxelSet voxelSet) {
        throw new AssertionError();
    }

    @Override
    public VoxelShape zine$rotateXClockwise() {
        return zine$init(this.voxels.zine$rotateXClockwise());
    }

    @Override
    public VoxelShape zine$rotateXCounterclockwise() {
        return zine$init(this.voxels.zine$rotateXCounterclockwise());
    }

    @Override
    public VoxelShape zine$rotateXOpposite() {
        return zine$init(this.voxels.zine$rotateXOpposite());
    }

    @Override
    public VoxelShape zine$rotateYClockwise() {
        return zine$init(this.voxels.zine$rotateYClockwise());
    }

    @Override
    public VoxelShape zine$rotateYCounterclockwise() {
        return zine$init(this.voxels.zine$rotateYCounterclockwise());
    }

    @Override
    public VoxelShape zine$rotateYOpposite() {
        return zine$init(this.voxels.zine$rotateYOpposite());
    }

    @Override
    public VoxelShape zine$rotateZClockwise() {
        return zine$init(this.voxels.zine$rotateZClockwise());
    }

    @Override
    public VoxelShape zine$rotateZCounterclockwise() {
        return zine$init(this.voxels.zine$rotateZCounterclockwise());
    }

    @Override
    public VoxelShape zine$rotateZOpposite() {
        return zine$init(this.voxels.zine$rotateZOpposite());
    }

    @Override
    public VoxelShape zine$mirrorX() {
        return zine$init(this.voxels.zine$mirrorX());
    }

    @Override
    public VoxelShape zine$mirrorY() {
        return zine$init(this.voxels.zine$mirrorY());
    }

    @Override
    public VoxelShape zine$mirrorZ() {
        return zine$init(this.voxels.zine$mirrorZ());
    }
}
