package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.util.function.Pos3iToIntFunction;
import net.minecraft.util.shape.BitSetVoxelSet;
import net.minecraft.util.shape.VoxelSet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.BitSet;

@SuppressWarnings({"ReassignedVariable", "SuspiciousNameCombination"})
@Mixin(BitSetVoxelSet.class)
public abstract class BitSetVoxelSetMixin extends VoxelSetMixin {

    @Shadow @Final public BitSet storage;
    @Shadow public int minX;
    @Shadow public int minY;
    @Shadow public int minZ;
    @Shadow public int maxX;
    @Shadow public int maxY;
    @Shadow public int maxZ;

    @Shadow public abstract int getIndex(int x, int y, int z);

    @Unique
    private VoxelSet zine$transform(int sizeX, int sizeY, int sizeZ,
                                    int minX, int minY, int minZ,
                                    int maxX, int maxY, int maxZ,
                                    Pos3iToIntFunction indexFunction) {
        BitSetVoxelSet voxelSet = new BitSetVoxelSet(sizeX, sizeY, sizeZ);
        voxelSet.minX = minX;
        voxelSet.minY = minY;
        voxelSet.minZ = minZ;
        voxelSet.maxX = maxX;
        voxelSet.maxY = maxY;
        voxelSet.maxZ = maxZ;
        for(int x = 0; x < sizeX; x++) {
            for(int y = 0; y < sizeY; y++) {
                for(int z = 0; z < sizeZ; z++) {
                    voxelSet.storage.set(voxelSet.getIndex(x, y, z), this.storage.get(indexFunction.apply(x, y, z)));
                }
            }
        }
        return voxelSet;
    }

    @Override
    public VoxelSet zine$rotateXClockwise() {
        return this.zine$transform(
                this.sizeX, this.sizeZ, this.sizeY,
                this.minX, this.minZ, this.sizeY - this.maxY,
                this.maxX, this.maxZ, this.sizeY - this.minY,
                (x, y, z) -> this.getIndex(x, this.sizeY - z - 1, y)
        );
    }

    @Override
    public VoxelSet zine$rotateXCounterclockwise() {
        return this.zine$transform(
                this.sizeX, this.sizeZ, this.sizeY,
                this.minX, this.sizeZ - this.maxZ, this.minY,
                this.maxX, this.sizeZ - this.minZ, this.maxY,
                (x, y, z) -> this.getIndex(x, z, this.sizeZ - y - 1)
        );
    }

    @Override
    public VoxelSet zine$rotateXOpposite() {
        return this.zine$transform(
                this.sizeX, this.sizeY, this.sizeZ,
                this.minX, this.sizeY - this.maxY, this.sizeZ - this.maxZ,
                this.maxX, this.sizeY - this.minY, this.sizeZ - this.minZ,
                (x, y, z) -> this.getIndex(x, this.sizeY - y - 1, this.sizeZ - z - 1)
        );
    }

    @Override
    public VoxelSet zine$rotateYClockwise() {
        return this.zine$transform(
                this.sizeZ, this.sizeY, this.sizeX,
                this.sizeZ - this.maxZ, this.minY, this.minX,
                this.sizeZ - this.minZ, this.maxY, this.maxX,
                (x, y, z) -> this.getIndex(z, y, this.sizeZ - x - 1)
        );
    }

    @Override
    public VoxelSet zine$rotateYCounterclockwise() {
        return this.zine$transform(
                this.sizeZ, this.sizeY, this.sizeX,
                this.minZ, this.minY, this.sizeX - this.maxX,
                this.maxZ, this.maxY, this.sizeX - this.minX,
                (x, y, z) -> this.getIndex(this.sizeX - z - 1, y, x)
        );
    }

    @Override
    public VoxelSet zine$rotateYOpposite() {
        return this.zine$transform(
                this.sizeX, this.sizeY, this.sizeZ,
                this.sizeX - this.maxX, this.minY, this.sizeZ - this.maxZ,
                this.sizeX - this.minX, this.maxY, this.sizeZ - this.minZ,
                (x, y, z) -> this.getIndex(this.sizeX - x - 1, y, this.sizeZ - z - 1)
        );
    }

    @Override
    public VoxelSet zine$rotateZClockwise() {
        return this.zine$transform(
                this.sizeY, this.sizeX, this.sizeZ,
                this.minY, this.sizeX - this.maxX, this.minZ,
                this.maxY, this.sizeX - this.minX, this.maxZ,
                (x, y, z) -> this.getIndex(this.sizeX - y - 1, x, z)
        );
    }

    @Override
    public VoxelSet zine$rotateZCounterclockwise() {
        return this.zine$transform(
                this.sizeY, this.sizeX, this.sizeZ,
                this.sizeY - this.maxY, this.minX, this.minZ,
                this.sizeY - this.minY, this.maxX, this.maxZ,
                (x, y, z) -> this.getIndex(y, this.sizeY - x - 1, z)
        );
    }

    @Override
    public VoxelSet zine$rotateZOpposite() {
        return this.zine$transform(
                this.sizeX, this.sizeY, this.sizeZ,
                this.sizeX - this.maxX, this.sizeY - this.maxY, this.minZ,
                this.sizeX - this.minX, this.sizeY - this.minY, this.maxZ,
                (x, y, z) -> this.getIndex(this.sizeX - x - 1, this.sizeY - y - 1, z)
        );
    }

    @Override
    public VoxelSet zine$mirrorX() {
        return this.zine$transform(
                this.sizeX, this.sizeY, this.sizeZ,
                this.sizeX - this.maxX, this.minY, this.minZ,
                this.sizeX - this.minX, this.maxY, this.maxZ,
                (x, y, z) -> this.getIndex(this.sizeX - x - 1, y, z)
        );
    }

    @Override
    public VoxelSet zine$mirrorY() {
        return this.zine$transform(
                this.sizeX, this.sizeY, this.sizeZ,
                this.minX, this.sizeY - this.maxY, this.minZ,
                this.maxX, this.sizeY - this.minY, this.maxZ,
                (x, y, z) -> this.getIndex(x, this.sizeY - y - 1, z)
        );
    }

    @Override
    public VoxelSet zine$mirrorZ() {
        return this.zine$transform(
                this.sizeX, this.sizeY, this.sizeZ,
                this.minX, this.minY, this.sizeZ - this.maxZ,
                this.maxX, this.maxY, this.sizeZ - this.minZ,
                (x, y, z) -> this.getIndex(x, y, this.sizeZ - z - 1)
        );
    }
}
