package com.eightsidedsquare.zine.common.entity.variant;

import net.minecraft.entity.passive.PigVariant;
import net.minecraft.entity.spawn.SpawnConditionSelectors;
import net.minecraft.util.ModelAndTexture;

public interface ZinePigVariant {

    default void zine$setModelAndTexture(ModelAndTexture<PigVariant.Model> modelAndTexture) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setSpawnConditions(SpawnConditionSelectors spawnConditions) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
