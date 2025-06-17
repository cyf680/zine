package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineStateCriterionConditions;
import net.minecraft.advancement.criterion.EnterBlockCriterion;
import net.minecraft.advancement.criterion.SlideDownBlockCriterion;
import net.minecraft.predicate.StatePredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(value = {
        EnterBlockCriterion.Conditions.class,
        SlideDownBlockCriterion.Conditions.class
})
public abstract class StateCriterionConditionsMixin implements ZineStateCriterionConditions {

    @Shadow(remap = false) @Final @Mutable
    private Optional<StatePredicate> state;

    @Override
    public void zine$setState(@Nullable StatePredicate state) {
        this.state = Optional.ofNullable(state);
    }
}
