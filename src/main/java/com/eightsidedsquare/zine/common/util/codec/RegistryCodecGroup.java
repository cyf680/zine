package com.eightsidedsquare.zine.common.util.codec;

import com.mojang.serialization.Codec;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;

/**
 * <p>A RegistryCodecGroup holds codecs and packet codecs which can be useful when working with a dynamic registry.
 * <p>The Tracked subtype registers a tracked data handler for a registry entry.
 */
public interface RegistryCodecGroup<T> {

    static <T> RegistryCodecGroup<T> create(RegistryKey<Registry<T>> registryKey, Codec<T> codec, Codec<T> networkCodec) {
        return new RegistryCodecGroupImpl<>(registryKey, codec, networkCodec);
    }

    static <T> RegistryCodecGroup<T> create(RegistryKey<Registry<T>> registryKey, Codec<T> codec) {
        return new RegistryCodecGroupImpl<>(registryKey, codec, codec);
    }

    static <T> RegistryCodecGroup.Tracked<T> createTracked(RegistryKey<Registry<T>> registryKey, Codec<T> codec, Codec<T> networkCodec) {
        return new RegistryCodecGroupImpl.TrackedImpl<>(registryKey, codec, networkCodec);
    }

    static <T> RegistryCodecGroup.Tracked<T> createTracked(RegistryKey<Registry<T>> registryKey, Codec<T> codec) {
        return new RegistryCodecGroupImpl.TrackedImpl<>(registryKey, codec, codec);
    }

    RegistryKey<Registry<T>> registryKey();

    Codec<T> codec();

    Codec<T> networkCodec();

    Codec<RegistryEntry<T>> entryCodec();

    PacketCodec<RegistryByteBuf, RegistryEntry<T>> packetCodec();

    interface Tracked<T> extends RegistryCodecGroup<T> {

        TrackedDataHandler<RegistryEntry<T>> trackedDataHandler();

    }

}
