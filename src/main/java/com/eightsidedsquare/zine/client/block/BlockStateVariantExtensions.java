package com.eightsidedsquare.zine.client.block;

import net.minecraft.block.Block;
import net.minecraft.client.data.BlockStateVariant;
import net.minecraft.client.data.ModelIds;
import net.minecraft.client.data.VariantSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public interface BlockStateVariantExtensions {

    default BlockStateVariant zine$model(Identifier model) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default BlockStateVariant zine$model(Item item) {
        return this.zine$model(ModelIds.getItemModelId(item));
    }

    default BlockStateVariant zine$model(Block block) {
        return this.zine$model(ModelIds.getBlockModelId(block));
    }

    default BlockStateVariant zine$model(Identifier model, String suffix) {
        return this.zine$model(model.withSuffixedPath(suffix));
    }

    default BlockStateVariant zine$model(Item item, String suffix) {
        return this.zine$model(ModelIds.getItemSubModelId(item, suffix));
    }

    default BlockStateVariant zine$model(Block block, String suffix) {
        return this.zine$model(ModelIds.getBlockSubModelId(block, suffix));
    }

    default BlockStateVariant zine$x(VariantSettings.Rotation rotation) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default BlockStateVariant zine$x(int rotation) {
        return this.zine$x(rotation(rotation));
    }

    default BlockStateVariant zine$y(VariantSettings.Rotation rotation) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default BlockStateVariant zine$y(int rotation) {
        return this.zine$y(rotation(rotation));
    }

    default BlockStateVariant zine$uvlock(boolean uvlock) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default BlockStateVariant zine$uvlock() {
        return this.zine$uvlock(true);
    }

    default BlockStateVariant zine$weight(int weight) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    private static VariantSettings.Rotation rotation(int rotation) {
        return switch (rotation) {
            case 90, 1 -> VariantSettings.Rotation.R90;
            case 180, 2 -> VariantSettings.Rotation.R180;
            case 270, 3 -> VariantSettings.Rotation.R270;
            default -> VariantSettings.Rotation.R0;
        };
    }

    default BlockStateVariant zine$upDefault(Direction direction) {
        return switch (direction) {
            case NORTH -> this.zine$x(90);
            case EAST -> this.zine$x(90).zine$y(90);
            case SOUTH -> this.zine$x(90).zine$y(180);
            case WEST -> this.zine$x(90).zine$y(270);
            case DOWN -> this.zine$x(180);
            default -> this.zine$x(0);
        };
    }

    default BlockStateVariant zine$northDefault(Direction direction) {
        return switch (direction) {
            case EAST -> this.zine$y(90);
            case SOUTH -> this.zine$y(180);
            case WEST -> this.zine$y(270);
            case UP -> this.zine$x(270);
            case DOWN -> this.zine$x(90);
            default -> this.zine$y(0);
        };
    }

    default BlockStateVariant zine$southDefault(Direction direction) {
        return switch (direction) {
            case NORTH -> this.zine$y(180);
            case EAST -> this.zine$y(270);
            case WEST -> this.zine$y(90);
            case UP -> this.zine$x(90);
            case DOWN -> this.zine$x(270);
            default -> this.zine$y(0);
        };
    }

}
