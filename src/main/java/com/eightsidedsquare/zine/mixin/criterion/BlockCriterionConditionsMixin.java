package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineBlockCriterionConditions;
import net.minecraft.advancement.criterion.BeeNestDestroyedCriterion;
import net.minecraft.advancement.criterion.EnterBlockCriterion;
import net.minecraft.advancement.criterion.SlideDownBlockCriterion;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(value = {
        BeeNestDestroyedCriterion.Conditions.class,
        EnterBlockCriterion.Conditions.class,
        SlideDownBlockCriterion.Conditions.class
})
public abstract class BlockCriterionConditionsMixin implements ZineBlockCriterionConditions {

    @Shadow(remap = false) @Final @Mutable
    private Optional<RegistryEntry<Block>> block;

    @Override
    public void zine$setBlock(@Nullable RegistryEntry<Block> block) {
        this.block = Optional.ofNullable(block);
    }

    @Override
    public void zine$setBlock(@Nullable Block block) {
        this.block = Optional.ofNullable(block == null ? null : Registries.BLOCK.getEntry(block));
    }
}
