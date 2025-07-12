package com.eightsidedsquare.zine.common.block;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

@FunctionalInterface
public interface ModifyBlockSoundGroupCallback {

    Event<ModifyBlockSoundGroupCallback> EVENT = EventFactory.createArrayBacked(
            ModifyBlockSoundGroupCallback.class,
            callbacks -> ctx -> {
                for (ModifyBlockSoundGroupCallback callback : callbacks) {
                    callback.modify(ctx);
                }
            }
    );

    void modify(Context ctx);

    interface Context {

        void setSoundGroup(Block block, BlockSoundGroup soundGroup);

        default void setSoundGroup(BlockSoundGroup soundGroup, Block... blocks) {
            for (Block block : blocks) {
                this.setSoundGroup(block, soundGroup);
            }
        }

        default Registry<Block> registry() {
            return Registries.BLOCK;
        }

    }

}
