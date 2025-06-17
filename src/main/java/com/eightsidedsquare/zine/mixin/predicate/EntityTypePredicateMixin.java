package com.eightsidedsquare.zine.mixin.predicate;

import com.eightsidedsquare.zine.common.predicate.ZineEntityTypePredicate;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityTypePredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.function.Function;

@Mixin(EntityTypePredicate.class)
public abstract class EntityTypePredicateMixin implements ZineEntityTypePredicate {

    @Shadow @Final @Mutable
    private RegistryEntryList<EntityType<?>> types;

    @Override
    public void zine$setTypes(RegistryEntryList<EntityType<?>> types) {
        this.types = types;
    }

    @Override
    public void zine$addType(EntityType<?> type) {
        this.types = ZineUtil.mergeValue(this.types, Registries.ENTITY_TYPE::getEntry, type);
    }

    @Override
    public void zine$addType(RegistryEntry<EntityType<?>> type) {
        this.types = ZineUtil.mergeValue(this.types, Function.identity(), type);
    }

    @Override
    public void zine$addTypes(RegistryEntryList<EntityType<?>> types) {
        this.types = ZineUtil.mergeValues(this.types, types);
    }

    @Override
    public void zine$addTypes(Collection<EntityType<?>> types) {
        this.types= ZineUtil.mergeValues(this.types, Registries.ENTITY_TYPE::getEntry, types);
    }
}
