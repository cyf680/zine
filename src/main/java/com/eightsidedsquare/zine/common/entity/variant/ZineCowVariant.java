package com.eightsidedsquare.zine.common.entity.variant;

import net.minecraft.entity.passive.CowVariant;
import net.minecraft.entity.spawn.SpawnConditionSelectors;
import net.minecraft.util.ModelAndTexture;

public interface ZineCowVariant {

    default void zine$setModelAndTexture(ModelAndTexture<CowVariant.Model> modelAndTexture) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setSpawnConditions(SpawnConditionSelectors spawnConditions) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
