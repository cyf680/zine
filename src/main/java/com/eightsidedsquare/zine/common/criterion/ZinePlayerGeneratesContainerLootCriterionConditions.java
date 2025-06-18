package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;

public interface ZinePlayerGeneratesContainerLootCriterionConditions {

    default void zine$setLootTable(RegistryKey<LootTable> lootTable) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
