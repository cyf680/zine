package com.eightsidedsquare.zine.common.world.structure;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;

import java.util.List;
import java.util.function.Function;

public interface ZineStructurePool {

    default ObjectArrayList<StructurePoolElement> zine$getElements() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default List<Pair<StructurePoolElement, Integer>> zine$getElementWeights() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addElement(StructurePoolElement element, int weight) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addElement(
            Function<StructurePool.Projection, ? extends StructurePoolElement> elementGetter,
            int weight,
            StructurePool.Projection projection) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setFallback(RegistryEntry<StructurePool> fallback) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
