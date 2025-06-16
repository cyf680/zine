package com.eightsidedsquare.zine.common.enchantment;

import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.entry.RegistryEntryList;
import org.jetbrains.annotations.Nullable;

public interface ZineEnchantmentBuilder {

    default Enchantment.Definition zine$getDefinition() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Enchantment.Builder zine$definition(Enchantment.Definition definition) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default RegistryEntryList<Enchantment> zine$getExclusiveSet() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default ComponentMap.Builder zine$getEffectMap() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Enchantment.Builder zine$effectMap(ComponentMap.Builder effectMap) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    @Nullable
    default <T> T zine$getEffect(ComponentType<T> type) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
