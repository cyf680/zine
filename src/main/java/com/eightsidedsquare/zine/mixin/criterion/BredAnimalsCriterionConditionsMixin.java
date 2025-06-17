package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineBredAnimalsCriterionConditions;
import net.minecraft.advancement.criterion.BredAnimalsCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(BredAnimalsCriterion.Conditions.class)
public abstract class BredAnimalsCriterionConditionsMixin implements ZineBredAnimalsCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> parent;

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> partner;

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> child;

    @Override
    public void zine$setParent(@Nullable LootContextPredicate parent) {
        this.parent = Optional.ofNullable(parent);
    }

    @Override
    public void zine$setPartner(@Nullable LootContextPredicate partner) {
        this.partner = Optional.ofNullable(partner);
    }

    @Override
    public void zine$setChild(@Nullable LootContextPredicate child) {
        this.child = Optional.ofNullable(child);
    }

}
