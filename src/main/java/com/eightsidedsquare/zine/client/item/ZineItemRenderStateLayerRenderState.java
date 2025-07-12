package com.eightsidedsquare.zine.client.item;

import net.minecraft.client.util.math.MatrixStack;

import java.util.function.Consumer;

public interface ZineItemRenderStateLayerRenderState {

    default void zine$setMatrixTransformation(boolean beforeDisplayTransforms, Consumer<MatrixStack> matrixTransformation) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setMatrixTransformation(Consumer<MatrixStack> matrixTransformation) {
        this.zine$setMatrixTransformation(false, matrixTransformation);
    }

}
