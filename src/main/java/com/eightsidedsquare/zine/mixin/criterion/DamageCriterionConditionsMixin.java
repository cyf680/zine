package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineDamageCriterionConditions;
import net.minecraft.advancement.criterion.EntityHurtPlayerCriterion;
import net.minecraft.advancement.criterion.PlayerHurtEntityCriterion;
import net.minecraft.predicate.DamagePredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(value = {
        EntityHurtPlayerCriterion.Conditions.class,
        PlayerHurtEntityCriterion.Conditions.class
})
public abstract class DamageCriterionConditionsMixin implements ZineDamageCriterionConditions {

    @Shadow(remap = false) @Final @Mutable
    private Optional<DamagePredicate> damage;

    @Override
    public void zine$setDamage(@Nullable DamagePredicate damage) {
        this.damage = Optional.ofNullable(damage);
    }
}
