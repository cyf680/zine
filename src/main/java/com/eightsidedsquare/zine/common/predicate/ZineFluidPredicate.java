package com.eightsidedsquare.zine.common.predicate;

import net.minecraft.fluid.Fluid;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.entry.RegistryEntryList;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface ZineFluidPredicate {

    default void zine$setFluids(@Nullable RegistryEntryList<Fluid> fluids) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addFluid(Fluid fluid) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addFluids(Collection<Fluid> fluids) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setState(@Nullable StatePredicate state) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
