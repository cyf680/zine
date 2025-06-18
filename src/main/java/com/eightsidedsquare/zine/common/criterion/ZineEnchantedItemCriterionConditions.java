package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.NumberRange;

public interface ZineEnchantedItemCriterionConditions {

    default void zine$setLevels(NumberRange.IntRange levels) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
