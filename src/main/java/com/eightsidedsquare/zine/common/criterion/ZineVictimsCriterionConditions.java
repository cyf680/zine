package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.LootContextPredicate;

import java.util.Collection;
import java.util.List;

public interface ZineVictimsCriterionConditions {

    default void zine$setVictims(List<LootContextPredicate> victims) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addVictim(LootContextPredicate victim) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addVictims(Collection<LootContextPredicate> victims) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
