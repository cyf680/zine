package com.eightsidedsquare.zine.mixin.predicate;

import com.eightsidedsquare.zine.common.predicate.ZineDistancePredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.DistancePredicate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DistancePredicate.class)
public abstract class DistancePredicateMixin implements ZineDistancePredicate {

    @Shadow @Final @Mutable
    private NumberRange.DoubleRange x;

    @Shadow @Final @Mutable
    private NumberRange.DoubleRange y;

    @Shadow @Final @Mutable
    private NumberRange.DoubleRange z;

    @Shadow @Final @Mutable
    private NumberRange.DoubleRange horizontal;

    @Shadow @Final @Mutable
    private NumberRange.DoubleRange absolute;

    @Override
    public void zine$setX(NumberRange.DoubleRange x) {
        this.x = x;
    }

    @Override
    public void zine$setY(NumberRange.DoubleRange y) {
        this.y = y;
    }

    @Override
    public void zine$setZ(NumberRange.DoubleRange z) {
        this.z = z;
    }

    @Override
    public void zine$setHorizontal(NumberRange.DoubleRange horizontal) {
        this.horizontal = horizontal;
    }

    @Override
    public void zine$setAbsolute(NumberRange.DoubleRange absolute) {
        this.absolute = absolute;
    }
}
