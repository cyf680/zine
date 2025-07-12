package com.eightsidedsquare.zine.common.block;

import com.eightsidedsquare.zine.common.registry.FreezeRegistriesEvents;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;

public final class ModifyBlockSoundGroupContextImpl implements ModifyBlockSoundGroupCallback.Context {

    public static void registerEvents() {
        FreezeRegistriesEvents.beforeFreeze(RegistryKeys.BLOCK)
                .register(registry ->
                        ModifyBlockSoundGroupCallback.EVENT.invoker().modify(new ModifyBlockSoundGroupContextImpl())
                );
    }

    @Override
    public void setSoundGroup(Block block, BlockSoundGroup soundGroup) {
        block.soundGroup = soundGroup;
    }

    private ModifyBlockSoundGroupContextImpl() {
    }
}
