package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.predicate.entity.EntityEffectPredicate;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Map;

public interface ZineEntityEffectPredicate {

    default void zine$setEffects(Map<RegistryEntry<StatusEffect>, EntityEffectPredicate.EffectData> effects) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addEffect(RegistryEntry<StatusEffect> effect, EntityEffectPredicate.EffectData data) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addEffect(RegistryEntry<StatusEffect> effect) {
        this.zine$addEffect(effect, new EntityEffectPredicate.EffectData());
    }

}
