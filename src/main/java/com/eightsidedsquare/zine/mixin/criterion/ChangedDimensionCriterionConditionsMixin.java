package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineChangedDimensionCriterionConditions;
import net.minecraft.advancement.criterion.ChangedDimensionCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(ChangedDimensionCriterion.Conditions.class)
public abstract class ChangedDimensionCriterionConditionsMixin implements ZineChangedDimensionCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<RegistryKey<World>> from;

    @Shadow @Final @Mutable
    private Optional<RegistryKey<World>> to;

    @Override
    public void zine$setFrom(@Nullable RegistryKey<World> from) {
        this.from = Optional.ofNullable(from);
    }

    @Override
    public void zine$setTo(@Nullable RegistryKey<World> to) {
        this.to = Optional.ofNullable(to);
    }
}
