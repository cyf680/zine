package com.eightsidedsquare.zine.client.atlas;

public interface NativeImageExtensions {

    default long zine$getPointer() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
