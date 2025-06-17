package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineUsedEnderEyeCriterionConditions;
import net.minecraft.advancement.criterion.UsedEnderEyeCriterion;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(UsedEnderEyeCriterion.Conditions.class)
public abstract class UsedEnderEyeCriterionConditionsMixin implements ZineUsedEnderEyeCriterionConditions {

    @Shadow @Final @Mutable
    private NumberRange.DoubleRange distance;

    @Override
    public void zine$setDistance(NumberRange.DoubleRange distance) {
        this.distance = distance;
    }
}
