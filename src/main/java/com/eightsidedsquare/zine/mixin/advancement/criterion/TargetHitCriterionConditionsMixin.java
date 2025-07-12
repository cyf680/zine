package com.eightsidedsquare.zine.mixin.advancement.criterion;

import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZineTargetHitCriterionConditions;
import net.minecraft.advancement.criterion.TargetHitCriterion;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(TargetHitCriterion.Conditions.class)
public abstract class TargetHitCriterionConditionsMixin implements ZinePlayerCriterionConditions,
        ZineTargetHitCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Shadow @Final @Mutable
    private NumberRange.IntRange signalStrength;

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> projectile;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }

    @Override
    public void zine$setSignalStrength(NumberRange.IntRange signalStrength) {
        this.signalStrength = signalStrength;
    }

    @Override
    public void zine$setProjectile(@Nullable LootContextPredicate projectile) {
        this.projectile = Optional.ofNullable(projectile);
    }
}
