package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.util.shape.VoxelSetExtensions;
import net.minecraft.util.shape.VoxelSet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(VoxelSet.class)
public abstract class VoxelSetMixin implements VoxelSetExtensions {

    @Shadow @Final protected int sizeX;
    @Shadow @Final protected int sizeY;
    @Shadow @Final protected int sizeZ;

    @Override
    public VoxelSet zine$rotateXClockwise() {
        return (VoxelSet) (Object) this;
    }

    @Override
    public VoxelSet zine$rotateXCounterclockwise() {
        return (VoxelSet) (Object) this;
    }

    @Override
    public VoxelSet zine$rotateXOpposite() {
        return (VoxelSet) (Object) this;
    }

    @Override
    public VoxelSet zine$rotateYClockwise() {
        return (VoxelSet) (Object) this;
    }

    @Override
    public VoxelSet zine$rotateYCounterclockwise() {
        return (VoxelSet) (Object) this;
    }

    @Override
    public VoxelSet zine$rotateYOpposite() {
        return (VoxelSet) (Object) this;
    }

    @Override
    public VoxelSet zine$rotateZClockwise() {
        return (VoxelSet) (Object) this;
    }

    @Override
    public VoxelSet zine$rotateZCounterclockwise() {
        return (VoxelSet) (Object) this;
    }

    @Override
    public VoxelSet zine$rotateZOpposite() {
        return (VoxelSet) (Object) this;
    }

    @Override
    public VoxelSet zine$mirrorX() {
        return (VoxelSet) (Object) this;
    }

    @Override
    public VoxelSet zine$mirrorY() {
        return (VoxelSet) (Object) this;
    }

    @Override
    public VoxelSet zine$mirrorZ() {
        return (VoxelSet) (Object) this;
    }
}
