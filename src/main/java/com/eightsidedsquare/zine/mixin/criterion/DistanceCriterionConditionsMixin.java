package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineDistanceCriterionConditions;
import net.minecraft.advancement.criterion.FallAfterExplosionCriterion;
import net.minecraft.advancement.criterion.LevitationCriterion;
import net.minecraft.advancement.criterion.TravelCriterion;
import net.minecraft.predicate.entity.DistancePredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(value = {
        FallAfterExplosionCriterion.Conditions.class,
        LevitationCriterion.Conditions.class,
        TravelCriterion.Conditions.class
})
public abstract class DistanceCriterionConditionsMixin implements ZineDistanceCriterionConditions {

    @Shadow(remap = false) @Final @Mutable
    private Optional<DistancePredicate> distance;

    @Override
    public void zine$setDistance(@Nullable DistancePredicate distance) {
        this.distance = Optional.ofNullable(distance);
    }
}
