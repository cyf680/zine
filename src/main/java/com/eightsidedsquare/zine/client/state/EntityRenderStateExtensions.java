package com.eightsidedsquare.zine.client.state;

public interface EntityRenderStateExtensions {

    default <T> T zine$getData(RenderStateDataKey<T> key) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default <T> void zine$setData(RenderStateDataKey<T> key, T value) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$clearData() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
