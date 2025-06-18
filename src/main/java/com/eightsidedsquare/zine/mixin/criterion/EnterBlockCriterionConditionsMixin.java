package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineBlockCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZineStateCriterionConditions;
import net.minecraft.advancement.criterion.EnterBlockCriterion;
import net.minecraft.block.Block;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(EnterBlockCriterion.Conditions.class)
public abstract class EnterBlockCriterionConditionsMixin implements ZinePlayerCriterionConditions,
        ZineBlockCriterionConditions,
        ZineStateCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Shadow @Final @Mutable
    private Optional<RegistryEntry<Block>> block;

    @Shadow @Final @Mutable
    private Optional<StatePredicate> state;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }

    @Override
    public void zine$setBlock(@Nullable RegistryEntry<Block> block) {
        this.block = Optional.ofNullable(block);
    }

    @Override
    public void zine$setState(@Nullable StatePredicate state) {
        this.state = Optional.ofNullable(state);
    }
}
