package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.block.Block;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;

public interface ZineBeeNestDestroyedCriterionConditions {

    default void zine$setBeesInside(NumberRange.IntRange beesInside) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
