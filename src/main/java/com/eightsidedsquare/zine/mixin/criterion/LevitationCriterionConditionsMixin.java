package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineDistanceCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZineLevitationCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import net.minecraft.advancement.criterion.LevitationCriterion;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.DistancePredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(LevitationCriterion.Conditions.class)
public abstract class LevitationCriterionConditionsMixin implements ZinePlayerCriterionConditions,
        ZineDistanceCriterionConditions,
        ZineLevitationCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Shadow @Final @Mutable
    private Optional<DistancePredicate> distance;

    @Shadow @Final @Mutable
    private NumberRange.IntRange duration;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }

    @Override
    public void zine$setDistance(@Nullable DistancePredicate distance) {
        this.distance = Optional.ofNullable(distance);
    }

    @Override
    public void zine$setDuration(NumberRange.IntRange duration) {
        this.duration = duration;
    }
}
