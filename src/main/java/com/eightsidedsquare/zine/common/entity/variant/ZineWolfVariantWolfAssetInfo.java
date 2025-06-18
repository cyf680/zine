package com.eightsidedsquare.zine.common.entity.variant;

import net.minecraft.util.AssetInfo;

public interface ZineWolfVariantWolfAssetInfo {

    default void zine$setWild(AssetInfo wild) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setTame(AssetInfo tame) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setAngry(AssetInfo angry) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
