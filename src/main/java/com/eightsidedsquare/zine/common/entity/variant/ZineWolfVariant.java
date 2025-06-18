package com.eightsidedsquare.zine.common.entity.variant;

import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.entity.spawn.SpawnConditionSelectors;

public interface ZineWolfVariant {

    default void zine$setAssetInfo(WolfVariant.WolfAssetInfo assetInfo) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setSpawnConditions(SpawnConditionSelectors spawnConditions) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
