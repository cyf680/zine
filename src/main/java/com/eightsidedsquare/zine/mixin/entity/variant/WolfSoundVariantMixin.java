package com.eightsidedsquare.zine.mixin.entity.variant;

import com.eightsidedsquare.zine.common.entity.variant.ZineWolfSoundVariant;
import net.minecraft.entity.passive.WolfSoundVariant;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WolfSoundVariant.class)
public abstract class WolfSoundVariantMixin implements ZineWolfSoundVariant {

    @Shadow @Final @Mutable
    private RegistryEntry<SoundEvent> ambientSound;

    @Shadow @Final @Mutable
    private RegistryEntry<SoundEvent> deathSound;

    @Shadow @Final @Mutable
    private RegistryEntry<SoundEvent> growlSound;

    @Shadow @Final @Mutable
    private RegistryEntry<SoundEvent> hurtSound;

    @Shadow @Final @Mutable
    private RegistryEntry<SoundEvent> pantSound;

    @Shadow @Final @Mutable
    private RegistryEntry<SoundEvent> whineSound;

    @Override
    public void zine$setAmbientSound(RegistryEntry<SoundEvent> ambientSound) {
        this.ambientSound = ambientSound;
    }

    @Override
    public void zine$setDeathSound(RegistryEntry<SoundEvent> deathSound) {
        this.deathSound = deathSound;
    }

    @Override
    public void zine$setGrowlSound(RegistryEntry<SoundEvent> growlSound) {
        this.growlSound = growlSound;
    }

    @Override
    public void zine$setHurtSound(RegistryEntry<SoundEvent> hurtSound) {
        this.hurtSound = hurtSound;
    }

    @Override
    public void zine$setPantSound(RegistryEntry<SoundEvent> pantSound) {
        this.pantSound = pantSound;
    }

    @Override
    public void zine$setWhineSound(RegistryEntry<SoundEvent> whineSound) {
        this.whineSound = whineSound;
    }
}
