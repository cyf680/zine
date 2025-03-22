package com.eightsidedsquare.zine.client.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.data.BlockModelDefinitionCreator;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public final class BlockStateDefinitionEvents {
    private BlockStateDefinitionEvents() {
    }

    /**
     * Event for adding block state definitions to the game, in a similar manner to data generation.
     */
    public static final Event<AddDefinitions> ADD_DEFINITIONS = EventFactory.createArrayBacked(
            AddDefinitions.class,
            callbacks -> blockStateCollector -> {
        for(AddDefinitions callback : callbacks) {
            callback.addBlockStateDefinitions(blockStateCollector);
        }
    });

    @FunctionalInterface
    public interface AddDefinitions {
        /**
         * @param blockStateCollector a consumer that accepts block state suppliers
         * @see net.minecraft.client.data.MultipartBlockModelDefinitionCreator
         * @see net.minecraft.client.data.VariantsBlockModelDefinitionCreator
         */
        void addBlockStateDefinitions(Consumer<BlockModelDefinitionCreator> blockStateCollector);
    }
}
