package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineLocationCriterionConditions;
import net.minecraft.advancement.criterion.AnyBlockUseCriterion;
import net.minecraft.advancement.criterion.DefaultBlockUseCriterion;
import net.minecraft.advancement.criterion.ItemCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(value = {
        AnyBlockUseCriterion.Conditions.class,
        DefaultBlockUseCriterion.Conditions.class,
        ItemCriterion.Conditions.class
})
public abstract class LocationCriterionConditionsMixin implements ZineLocationCriterionConditions {

    @Shadow(remap = false) @Final @Mutable
    private Optional<LootContextPredicate> location;

    @Override
    public void zine$setLocation(@Nullable LootContextPredicate location) {
        this.location = Optional.ofNullable(location);
    }
}
