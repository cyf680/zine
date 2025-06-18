package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineLightningStrikeCriterionConditions {

    default void zine$setLightning(@Nullable LootContextPredicate lightning) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setBystander(@Nullable LootContextPredicate bystander) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
