package com.eightsidedsquare.zine.common.registry;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public final class FreezeRegistriesEvents {

    public static <T> Event<Callback<T>> beforeFreeze(RegistryKey<? extends Registry<T>> registryKey) {
        return FreezeRegistriesEventsImpl.getOrCreateEvent(true, registryKey);
    }

    public static <T> Event<Callback<T>> beforeFreeze(Registry<T> registry) {
        return beforeFreeze(registry.getKey());
    }

    public static <T> Event<Callback<T>> afterFreeze(RegistryKey<? extends Registry<T>> registryKey) {
        return FreezeRegistriesEventsImpl.getOrCreateEvent(false, registryKey);
    }

    public static <T> Event<Callback<T>> afterFreeze(Registry<T> registry) {
        return afterFreeze(registry.getKey());
    }

    @FunctionalInterface
    public interface Callback<T> {

        void onFreeze(Registry<T> registry);

    }

    private FreezeRegistriesEvents() {
    }

}
