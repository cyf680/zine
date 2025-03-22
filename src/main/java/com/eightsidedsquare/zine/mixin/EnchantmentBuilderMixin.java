package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.enchantment.EnchantmentBuilderExtensions;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.entry.RegistryEntryList;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Enchantment.Builder.class)
public abstract class EnchantmentBuilderMixin implements EnchantmentBuilderExtensions {

    @Shadow @Final @Mutable
    private Enchantment.Definition definition;

    @Shadow
    private RegistryEntryList<Enchantment> exclusiveSet;

    @Shadow @Final @Mutable
    private ComponentMap.Builder effectMap;

    @Override
    public Enchantment.Definition zine$getDefinition() {
        return this.definition;
    }

    @Override
    public Enchantment.Builder zine$definition(Enchantment.Definition definition) {
        this.definition = definition;
        return (Enchantment.Builder) (Object) this;
    }

    @Override
    public RegistryEntryList<Enchantment> zine$getExclusiveSet() {
        return this.exclusiveSet;
    }

    @Override
    public ComponentMap.Builder zine$getEffectMap() {
        return this.effectMap;
    }

    @Override
    public Enchantment.Builder zine$effectMap(ComponentMap.Builder effectMap) {
        this.effectMap = effectMap;
        return (Enchantment.Builder) (Object) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> @Nullable T zine$getEffect(ComponentType<T> type) {
        return (T) this.effectMap.components.get(type);
    }

}
