package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineLightningStrikeCriterionConditions;
import net.minecraft.advancement.criterion.LightningStrikeCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(LightningStrikeCriterion.Conditions.class)
public abstract class LightningStrikeCriterionConditionsMixin implements ZineLightningStrikeCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> lightning;

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> bystander;

    @Override
    public void zine$setLightning(@Nullable LootContextPredicate lightning) {
        this.lightning = Optional.ofNullable(lightning);
    }

    @Override
    public void zine$setBystander(@Nullable LootContextPredicate bystander) {
        this.bystander = Optional.ofNullable(bystander);
    }
}
