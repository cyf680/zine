package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineOnKilledCriterionConditions;
import net.minecraft.advancement.criterion.OnKilledCriterion;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(OnKilledCriterion.Conditions.class)
public abstract class OnKilledCriterionConditionsMixin implements ZineOnKilledCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<DamageSourcePredicate> killingBlow;

    @Override
    public void zine$setKillingBlow(@Nullable DamageSourcePredicate killingBlow) {
        this.killingBlow = Optional.ofNullable(killingBlow);
    }
}
