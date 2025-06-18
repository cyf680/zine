package com.eightsidedsquare.zine.mixin.predicate;

import com.eightsidedsquare.zine.common.predicate.ZineLightPredicate;
import net.minecraft.predicate.LightPredicate;
import net.minecraft.predicate.NumberRange;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LightPredicate.class)
public abstract class LightPredicateMixin implements ZineLightPredicate {

    @Shadow @Final @Mutable
    private NumberRange.IntRange range;

    @Override
    public void zine$setRange(NumberRange.IntRange range) {
        this.range = range;
    }
}
