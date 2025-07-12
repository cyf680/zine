package com.eightsidedsquare.zine.mixin.advancement.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineDistanceCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZineStartPositionCriterionConditions;
import net.minecraft.advancement.criterion.TravelCriterion;
import net.minecraft.predicate.entity.DistancePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(TravelCriterion.Conditions.class)
public abstract class TravelCriterionConditionsMixin implements ZinePlayerCriterionConditions,
        ZineStartPositionCriterionConditions,
        ZineDistanceCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Shadow @Final @Mutable
    private Optional<LocationPredicate> startPosition;

    @Shadow @Final @Mutable
    private Optional<DistancePredicate> distance;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }

    @Override
    public void zine$setStartPosition(@Nullable LocationPredicate startPosition) {
        this.startPosition = Optional.ofNullable(startPosition);
    }

    @Override
    public void zine$setDistance(@Nullable DistancePredicate distance) {
        this.distance = Optional.ofNullable(distance);
    }
}
