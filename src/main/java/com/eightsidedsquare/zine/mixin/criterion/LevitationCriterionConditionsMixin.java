package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineLevitationCriterionConditions;
import net.minecraft.advancement.criterion.LevitationCriterion;
import net.minecraft.predicate.NumberRange;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LevitationCriterion.Conditions.class)
public abstract class LevitationCriterionConditionsMixin implements ZineLevitationCriterionConditions {

    @Shadow @Final @Mutable
    private NumberRange.IntRange duration;

    @Override
    public void zine$setDuration(NumberRange.IntRange duration) {
        this.duration = duration;
    }
}
