package com.eightsidedsquare.zine.mixin.predicate;

import com.eightsidedsquare.zine.common.predicate.ZineBlockPredicate;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.block.Block;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntryList;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.Optional;

@Mixin(BlockPredicate.class)
public abstract class BlockPredicateMixin implements ZineBlockPredicate {

    @Shadow @Final @Mutable
    private Optional<RegistryEntryList<Block>> blocks;

    @Shadow @Final @Mutable
    private Optional<StatePredicate> state;

    @Shadow @Final @Mutable
    private Optional<NbtPredicate> nbt;

    @Override
    public void zine$setBlocks(@Nullable RegistryEntryList<Block> blocks) {
        this.blocks = Optional.ofNullable(blocks);
    }

    @Override
    public void zine$addBlock(Block block) {
        if(this.blocks.isPresent()) {
            this.blocks = Optional.of(ZineUtil.mergeValue(this.blocks.get(), Registries.BLOCK::getEntry, block));
            return;
        }
        this.blocks = Optional.of(RegistryEntryList.of(Registries.BLOCK::getEntry, block));
    }

    @Override
    public void zine$addBlocks(Collection<Block> blocks) {
        if(this.blocks.isPresent()) {
            this.blocks = Optional.of(ZineUtil.mergeValues(this.blocks.get(), Registries.BLOCK::getEntry, blocks));
            return;
        }
        this.blocks = Optional.of(RegistryEntryList.of(Registries.BLOCK::getEntry, blocks));
    }

    @Override
    public void zine$setState(@Nullable StatePredicate state) {
        this.state = Optional.ofNullable(state);
    }

    @Override
    public void zine$setNbt(@Nullable NbtPredicate nbt) {
        this.nbt = Optional.ofNullable(nbt);
    }
}
