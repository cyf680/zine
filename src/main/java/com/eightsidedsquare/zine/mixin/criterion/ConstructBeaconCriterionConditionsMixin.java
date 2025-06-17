package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineConstructBeaconCriterionConditions;
import net.minecraft.advancement.criterion.ConstructBeaconCriterion;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(ConstructBeaconCriterion.Conditions.class)
public abstract class ConstructBeaconCriterionConditionsMixin implements ZineConstructBeaconCriterionConditions {

    @Shadow @Final @Mutable
    private NumberRange.IntRange level;

    @Override
    public void zine$setLevel(NumberRange.IntRange level) {
        this.level = level;
    }
}
