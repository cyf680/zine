package com.eightsidedsquare.zine.mixin.advancement.criterion;

import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(TickCriterion.Conditions.class)
public abstract class TickCriterionConditionsMixin implements ZinePlayerCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }
}
