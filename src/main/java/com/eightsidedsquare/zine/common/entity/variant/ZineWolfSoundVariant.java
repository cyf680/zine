package com.eightsidedsquare.zine.common.entity.variant;

import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;

public interface ZineWolfSoundVariant {

    default void zine$setAmbientSound(RegistryEntry<SoundEvent> ambientSound) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setAmbientSound(SoundEvent ambientSound) {
        this.zine$setAmbientSound(Registries.SOUND_EVENT.getEntry(ambientSound));
    }

    default void zine$setDeathSound(RegistryEntry<SoundEvent> deathSound) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setDeathSound(SoundEvent deathSound) {
        this.zine$setAmbientSound(Registries.SOUND_EVENT.getEntry(deathSound));
    }

    default void zine$setGrowlSound(RegistryEntry<SoundEvent> growlSound) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setGrowlSound(SoundEvent growlSound) {
        this.zine$setAmbientSound(Registries.SOUND_EVENT.getEntry(growlSound));
    }

    default void zine$setHurtSound(RegistryEntry<SoundEvent> hurtSound) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setHurtSound(SoundEvent hurtSound) {
        this.zine$setAmbientSound(Registries.SOUND_EVENT.getEntry(hurtSound));
    }

    default void zine$setPantSound(RegistryEntry<SoundEvent> pantSound) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setPantSound(SoundEvent pantSound) {
        this.zine$setAmbientSound(Registries.SOUND_EVENT.getEntry(pantSound));
    }

    default void zine$setWhineSound(RegistryEntry<SoundEvent> whineSound) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setWhineSound(SoundEvent whineSound) {
        this.zine$setAmbientSound(Registries.SOUND_EVENT.getEntry(whineSound));
    }

}
