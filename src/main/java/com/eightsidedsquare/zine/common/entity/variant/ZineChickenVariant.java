package com.eightsidedsquare.zine.common.entity.variant;

import net.minecraft.entity.passive.ChickenVariant;
import net.minecraft.entity.spawn.SpawnConditionSelectors;
import net.minecraft.util.ModelAndTexture;

public interface ZineChickenVariant {

    default void zine$setModelAndTexture(ModelAndTexture<ChickenVariant.Model> modelAndTexture) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setSpawnConditions(SpawnConditionSelectors spawnConditions) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
