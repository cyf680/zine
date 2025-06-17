package com.eightsidedsquare.zine.mixin.predicate;

import com.eightsidedsquare.zine.common.predicate.ZineMovementPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.MovementPredicate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MovementPredicate.class)
public abstract class MovementPredicateMixin implements ZineMovementPredicate {

    @Shadow @Final @Mutable
    private NumberRange.DoubleRange x;

    @Shadow @Final @Mutable
    private NumberRange.DoubleRange y;

    @Shadow @Final @Mutable
    private NumberRange.DoubleRange z;

    @Shadow @Final @Mutable
    private NumberRange.DoubleRange speed;

    @Shadow @Final @Mutable
    private NumberRange.DoubleRange horizontalSpeed;

    @Shadow @Final @Mutable
    private NumberRange.DoubleRange verticalSpeed;

    @Shadow @Final @Mutable
    private NumberRange.DoubleRange fallDistance;

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
    public void zine$setSpeed(NumberRange.DoubleRange speed) {
        this.speed = speed;
    }

    @Override
    public void zine$setHorizontalSpeed(NumberRange.DoubleRange horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }

    @Override
    public void zine$setVerticalSpeed(NumberRange.DoubleRange verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    @Override
    public void zine$setFallDistance(NumberRange.DoubleRange fallDistance) {
        this.fallDistance = fallDistance;
    }

}
