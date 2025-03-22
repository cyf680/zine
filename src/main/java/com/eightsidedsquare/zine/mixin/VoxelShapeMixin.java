package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.util.shape.VoxelShapeExtensions;
import net.minecraft.util.shape.VoxelSet;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(VoxelShape.class)
public abstract class VoxelShapeMixin implements VoxelShapeExtensions {

    @Shadow @Final protected VoxelSet voxels;

    @Override
    public VoxelShape zine$rotateXClockwise() {
        return (VoxelShape) (Object) this;
    }

    @Override
    public VoxelShape zine$rotateXCounterclockwise() {
        return (VoxelShape) (Object) this;
    }

    @Override
    public VoxelShape zine$rotateXOpposite() {
        return (VoxelShape) (Object) this;
    }

    @Override
    public VoxelShape zine$rotateYClockwise() {
        return (VoxelShape) (Object) this;
    }

    @Override
    public VoxelShape zine$rotateYCounterclockwise() {
        return (VoxelShape) (Object) this;
    }

    @Override
    public VoxelShape zine$rotateYOpposite() {
        return (VoxelShape) (Object) this;
    }

    @Override
    public VoxelShape zine$rotateZClockwise() {
        return (VoxelShape) (Object) this;
    }

    @Override
    public VoxelShape zine$rotateZCounterclockwise() {
        return (VoxelShape) (Object) this;
    }

    @Override
    public VoxelShape zine$rotateZOpposite() {
        return (VoxelShape) (Object) this;
    }

    @Override
    public VoxelShape zine$mirrorX() {
        return (VoxelShape) (Object) this;
    }

    @Override
    public VoxelShape zine$mirrorY() {
        return (VoxelShape) (Object) this;
    }

    @Override
    public VoxelShape zine$mirrorZ() {
        return (VoxelShape) (Object) this;
    }
}
