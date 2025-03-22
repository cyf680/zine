package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.predicate.LightPredicateExtensions;
import net.minecraft.predicate.LightPredicate;
import net.minecraft.predicate.NumberRange;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LightPredicate.class)
public abstract class LightPredicateMixin implements LightPredicateExtensions {

    @Shadow @Final @Mutable
    private NumberRange.IntRange range;

    @Override
    public void zine$setRange(NumberRange.IntRange range) {
        this.range = range;
    }
}
