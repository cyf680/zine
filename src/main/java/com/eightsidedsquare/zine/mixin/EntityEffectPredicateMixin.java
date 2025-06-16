package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.predicate.ZineEntityEffectPredicate;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.predicate.entity.EntityEffectPredicate;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(EntityEffectPredicate.class)
public abstract class EntityEffectPredicateMixin implements ZineEntityEffectPredicate {

    @Shadow @Final @Mutable
    private Map<RegistryEntry<StatusEffect>, EntityEffectPredicate.EffectData> effects;

    @Override
    public void zine$setEffects(Map<RegistryEntry<StatusEffect>, EntityEffectPredicate.EffectData> effects) {
        this.effects = effects;
    }

    @Override
    public void zine$addEffect(RegistryEntry<StatusEffect> effect, EntityEffectPredicate.EffectData data) {
        this.effects = ZineUtil.putOrUnfreeze(this.effects, effect, data);
    }
}
