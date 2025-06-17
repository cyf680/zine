package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public interface ZineChangedDimensionCriterionConditions {

    default void zine$setFrom(@Nullable RegistryKey<World> from) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setTo(@Nullable RegistryKey<World> to) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
