package com.eightsidedsquare.zine.mixin.predicate;

import com.eightsidedsquare.zine.common.predicate.ZineNbtPredicate;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.NbtPredicate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NbtPredicate.class)
public abstract class NbtPredicateMixin implements ZineNbtPredicate {

    @Shadow @Final @Mutable
    private NbtCompound nbt;

    @Override
    public void zine$setNbt(NbtCompound nbt) {
        this.nbt = nbt;
    }
}
