package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.world.structure.SinglePoolElementExtensions;
import com.mojang.datafixers.util.Either;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.StructureLiquidSettings;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(SinglePoolElement.class)
public abstract class SinglePoolElementMixin implements SinglePoolElementExtensions {

    @Shadow @Final @Mutable
    protected Either<Identifier, StructureTemplate> location;

    @Shadow @Final @Mutable
    protected RegistryEntry<StructureProcessorList> processors;

    @Shadow @Final @Mutable
    protected Optional<StructureLiquidSettings> overrideLiquidSettings;

    @Override
    public void zine$setTemplate(Identifier template) {
        this.location = Either.left(template);
    }

    @Override
    public void zine$setTemplate(StructureTemplate template) {
        this.location = Either.right(template);
    }

    @Override
    public Either<Identifier, StructureTemplate> zine$getTemplate() {
        return this.location;
    }

    @Override
    public RegistryEntry<StructureProcessorList> zine$getProcessors() {
        return this.processors;
    }

    @Override
    public void zine$setProcessors(RegistryEntry<StructureProcessorList> processors) {
        this.processors = processors;
    }

    @Override
    public @Nullable StructureLiquidSettings zine$getOverrideLiquidSettings() {
        return this.overrideLiquidSettings.orElse(null);
    }

    @Override
    public void zine$setOverrideLiquidSettings(@Nullable StructureLiquidSettings overrideLiquidSettings) {
        this.overrideLiquidSettings = Optional.ofNullable(overrideLiquidSettings);
    }
}
