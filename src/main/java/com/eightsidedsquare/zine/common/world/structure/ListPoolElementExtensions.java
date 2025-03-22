package com.eightsidedsquare.zine.common.world.structure;

import net.minecraft.structure.pool.StructurePoolElement;

import java.util.List;

public interface ListPoolElementExtensions {

    default List<StructurePoolElement> zine$getElements() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setElements(List<StructurePoolElement> elements) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addElement(StructurePoolElement element) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addElements(List<StructurePoolElement> elements) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
