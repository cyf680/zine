package com.eightsidedsquare.zine.common.util.codec;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricTrackedDataRegistry;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryFixedCodec;

public record RegistryCodecGroupImpl<T>(RegistryKey<Registry<T>> registryKey,
                                        Codec<T> codec,
                                        Codec<T> networkCodec,
                                        Codec<RegistryEntry<T>> entryCodec,
                                        PacketCodec<RegistryByteBuf, RegistryEntry<T>> packetCodec)
        implements RegistryCodecGroup<T> {

    public RegistryCodecGroupImpl(RegistryKey<Registry<T>> registryKey, Codec<T> codec, Codec<T> networkCodec) {
        this(
                registryKey,
                codec,
                networkCodec,
                RegistryFixedCodec.of(registryKey),
                PacketCodecs.registryEntry(registryKey)
        );
    }

    public record TrackedImpl<T>(RegistryKey<Registry<T>> registryKey,
                                 Codec<T> codec,
                                 Codec<T> networkCodec,
                                 Codec<RegistryEntry<T>> entryCodec,
                                 PacketCodec<RegistryByteBuf, RegistryEntry<T>> packetCodec,
                                 TrackedDataHandler<RegistryEntry<T>> trackedDataHandler)
            implements RegistryCodecGroup.Tracked<T> {

        public TrackedImpl(RegistryKey<Registry<T>> registryKey,
                            Codec<T> codec,
                            Codec<T> networkCodec,
                            Codec<RegistryEntry<T>> entryCodec,
                            PacketCodec<RegistryByteBuf, RegistryEntry<T>> packetCodec) {
            this(registryKey, codec, networkCodec, entryCodec, packetCodec, TrackedDataHandler.create(packetCodec));
            FabricTrackedDataRegistry.register(registryKey.getValue(), this.trackedDataHandler);
        }

        public TrackedImpl(RegistryKey<Registry<T>> registryKey, Codec<T> codec, Codec<T> networkCodec) {
            this(
                    registryKey,
                    codec,
                    networkCodec,
                    RegistryFixedCodec.of(registryKey),
                    PacketCodecs.registryEntry(registryKey)
            );
        }
    }
}
