package com.eightsidedsquare.zine.common.registry;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.List;

public class RegistryQueueImpl<T> implements RegistryQueue<T> {

    private final Registry<T> registry;
    private final RegistryHelper registryHelper;
    private final List<Entry<T>> entries = new ObjectArrayList<>();

    RegistryQueueImpl(Registry<T> registry, RegistryHelper registryHelper) {
        this.registry = registry;
        this.registryHelper = registryHelper;
    }

    @Override
    public T add(String name, T value) {
        this.entries.add(new Entry<>(name, value));
        return value;
    }

    @Override
    public RegistryEntry.Reference<T> reference(String name, T value) {
        RegistryEntry.Reference<T> reference = RegistryEntry.Reference.standAlone(this.registry, this.registryHelper.key(this.registry.getKey(), name));
        reference.setValue(this.add(name, value));
        return reference;
    }

    @Override
    public void registerAll() {
        for(Entry<T> entry : this.entries) {
            this.registryHelper.register(this.registry, entry.name, entry.value);
        }
        this.entries.clear();
    }

    private record Entry<T>(String name, T value) {
    }
}
