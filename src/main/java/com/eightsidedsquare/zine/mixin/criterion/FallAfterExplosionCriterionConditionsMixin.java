package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineFallAfterExplosionCriterionConditions;
import net.minecraft.advancement.criterion.FallAfterExplosionCriterion;
import net.minecraft.predicate.entity.DistancePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(FallAfterExplosionCriterion.Conditions.class)
public abstract class FallAfterExplosionCriterionConditionsMixin implements ZineFallAfterExplosionCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> cause;

    @Override
    public void zine$setCause(@Nullable LootContextPredicate cause) {
        this.cause = Optional.ofNullable(cause);
    }
}
