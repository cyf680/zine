package com.eightsidedsquare.zine.common.util.shape;

public interface VoxelExtensions<T> {

    /**
     * Rotates 90 degrees clockwise on the X axis
     */
    default T zine$rotateXClockwise() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    /**
     * Rotates 90 degrees counterclockwise on the X axis
     */
    default T zine$rotateXCounterclockwise() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    /**
     * Rotates 180 degrees on the X axis
     */
    default T zine$rotateXOpposite() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    /**
     * Rotates 90 degrees clockwise on the Y axis
     */
    default T zine$rotateYClockwise() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    /**
     * Rotates 90 degrees counterclockwise on the Y axis
     */
    default T zine$rotateYCounterclockwise() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    /**
     * Rotates 180 degrees on the Y axis
     */
    default T zine$rotateYOpposite() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    /**
     * Rotates 90 degrees clockwise on the Z axis
     */
    default T zine$rotateZClockwise() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    /**
     * Rotates 90 degrees counterclockwise on the Z axis
     */
    default T zine$rotateZCounterclockwise() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    /**
     * Rotates 180 degrees on the Z axis
     */
    default T zine$rotateZOpposite() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    /**
     * Mirrors along the X axis
     */
    default T zine$mirrorX() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    /**
     * Mirrors along the Y axis
     */
    default T zine$mirrorY() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    /**
     * Mirrors along the Z axis
     */
    default T zine$mirrorZ() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
