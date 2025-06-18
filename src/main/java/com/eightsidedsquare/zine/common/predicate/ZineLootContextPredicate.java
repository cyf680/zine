package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.loot.condition.LootCondition;

import java.util.List;

public interface ZineLootContextPredicate {

    default List<LootCondition> zine$getConditions() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setConditions(List<LootCondition> conditions) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addCondition(LootCondition condition) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addConditions(List<LootCondition> conditions) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
