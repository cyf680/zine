package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.loot.LootTable;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;

public interface ZinePlayerGeneratesContainerLootCriterionConditions {

    default void zine$setLootTable(RegistryKey<LootTable> lootTable) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
