package com.eightsidedsquare.zine.common.world.structure;

import com.mojang.datafixers.util.Either;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.StructureLiquidSettings;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public interface SinglePoolElementExtensions {

    default void zine$setTemplate(Identifier template) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setTemplate(StructureTemplate template) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Either<Identifier, StructureTemplate> zine$getTemplate() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default RegistryEntry<StructureProcessorList> zine$getProcessors() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setProcessors(RegistryEntry<StructureProcessorList> processors) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    @Nullable
    default StructureLiquidSettings zine$getOverrideLiquidSettings() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setOverrideLiquidSettings(@Nullable StructureLiquidSettings overrideLiquidSettings) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
