package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.predicate.FluidPredicateExtensions;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.fluid.Fluid;
import net.minecraft.predicate.FluidPredicate;
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

@Mixin(FluidPredicate.class)
public abstract class FluidPredicateMixin implements FluidPredicateExtensions {

    @Shadow @Final @Mutable
    private Optional<RegistryEntryList<Fluid>> fluids;

    @Shadow @Final @Mutable
    private Optional<StatePredicate> state;

    @Override
    public void zine$setFluids(@Nullable RegistryEntryList<Fluid> fluids) {
        this.fluids = Optional.ofNullable(fluids);
    }

    @Override
    public void zine$addFluid(Fluid fluid) {
        if(this.fluids.isPresent()) {
            this.fluids = Optional.of(ZineUtil.mergeValue(this.fluids.get(), Registries.FLUID::getEntry, fluid));
            return;
        }
        this.fluids = Optional.of(RegistryEntryList.of(Registries.FLUID::getEntry, fluid));
    }

    @Override
    public void zine$addFluids(Collection<Fluid> fluids) {
        if(this.fluids.isPresent()) {
            this.fluids = Optional.of(ZineUtil.mergeValues(this.fluids.get(), Registries.FLUID::getEntry, fluids));
            return;
        }
        this.fluids = Optional.of(RegistryEntryList.of(Registries.FLUID::getEntry, fluids));
    }

    @Override
    public void zine$setState(@Nullable StatePredicate state) {
        this.state = Optional.ofNullable(state);
    }
}
