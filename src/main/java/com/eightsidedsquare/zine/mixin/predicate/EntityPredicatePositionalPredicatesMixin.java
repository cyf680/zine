package com.eightsidedsquare.zine.mixin.predicate;

import com.eightsidedsquare.zine.common.predicate.ZineEntityPredicatePositionalPredicates;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(EntityPredicate.PositionalPredicates.class)
public abstract class EntityPredicatePositionalPredicatesMixin implements ZineEntityPredicatePositionalPredicates {

    @Shadow @Final @Mutable
    Optional<LocationPredicate> located;

    @Shadow @Final @Mutable
    Optional<LocationPredicate> steppingOn;

    @Shadow @Final @Mutable
    Optional<LocationPredicate> affectsMovement;

    @Override
    public void zine$setLocated(@Nullable LocationPredicate located) {
        this.located = Optional.ofNullable(located);
    }

    @Override
    public void zine$setSteppingOn(@Nullable LocationPredicate steppingOn) {
        this.steppingOn = Optional.ofNullable(steppingOn);
    }

    @Override
    public void zine$setAffectsMovement(@Nullable LocationPredicate affectsMovement) {
        this.affectsMovement = Optional.ofNullable(affectsMovement);
    }
}
