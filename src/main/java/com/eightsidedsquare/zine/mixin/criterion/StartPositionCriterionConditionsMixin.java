package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineStartPositionCriterionConditions;
import net.minecraft.advancement.criterion.FallAfterExplosionCriterion;
import net.minecraft.advancement.criterion.TravelCriterion;
import net.minecraft.predicate.entity.LocationPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(value = {
        FallAfterExplosionCriterion.Conditions.class,
        TravelCriterion.Conditions.class
})
public abstract class StartPositionCriterionConditionsMixin implements ZineStartPositionCriterionConditions {

    @Shadow(remap = false) @Final @Mutable
    private Optional<LocationPredicate> startPosition;

    @Override
    public void zine$setStartPosition(@Nullable LocationPredicate startPosition) {
        this.startPosition = Optional.ofNullable(startPosition);
    }
}
