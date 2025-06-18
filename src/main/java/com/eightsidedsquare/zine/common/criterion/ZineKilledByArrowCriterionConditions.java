package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import org.jetbrains.annotations.Nullable;

public interface ZineKilledByArrowCriterionConditions {

    default void zine$setUniqueEntityTypes(NumberRange.IntRange uniqueEntityTypes) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setFiredFromWeapon(@Nullable ItemPredicate firedFromWeapon) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
