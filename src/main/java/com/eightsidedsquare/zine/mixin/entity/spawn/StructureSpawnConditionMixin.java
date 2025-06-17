package com.eightsidedsquare.zine.mixin.entity.spawn;

import com.eightsidedsquare.zine.common.entity.spawn.ZineStructureSpawnCondition;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.entity.spawn.StructureSpawnCondition;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.gen.structure.Structure;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.function.Function;

@Mixin(StructureSpawnCondition.class)
public abstract class StructureSpawnConditionMixin implements ZineStructureSpawnCondition {

    @Shadow @Final @Mutable
    private RegistryEntryList<Structure> requiredStructures;

    @Override
    public void zine$setRequiredStructures(RegistryEntryList<Structure> requiredStructures) {
        this.requiredStructures = requiredStructures;
    }

    @Override
    public void zine$addRequiredStructure(RegistryEntry<Structure> requiredStructure) {
        this.requiredStructures = ZineUtil.mergeValue(this.requiredStructures, Function.identity(), requiredStructure);
    }

    @Override
    public void zine$addRequiredStructure(RegistryEntryLookup<Structure> structureLookup, RegistryKey<Structure> requiredStructure) {
        this.requiredStructures = ZineUtil.mergeValue(this.requiredStructures, structureLookup::getOrThrow, requiredStructure);
    }

    @Override
    public void zine$addRequiredStructures(RegistryEntryLookup<Structure> structureLookup, Collection<RegistryKey<Structure>> requiredStructures) {
        this.requiredStructures = ZineUtil.mergeValues(this.requiredStructures, structureLookup::getOrThrow, requiredStructures);
    }

    @Override
    public void zine$addRequiredStructures(RegistryEntryList<Structure> requiredStructures) {
        this.requiredStructures = ZineUtil.mergeValues(this.requiredStructures, requiredStructures);
    }
}
