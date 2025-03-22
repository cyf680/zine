package com.eightsidedsquare.zine.data.sound;

import net.minecraft.sound.SoundEvent;

public interface SoundEntryConsumer {
    void accept(SoundEvent soundEvent, SoundEntryRecord soundEntry);
}
