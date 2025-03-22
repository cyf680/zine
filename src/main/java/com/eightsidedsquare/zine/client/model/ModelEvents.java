package com.eightsidedsquare.zine.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.data.ModelSupplier;
import net.minecraft.client.data.TextureMap;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

@Environment(EnvType.CLIENT)
public final class ModelEvents {
    private ModelEvents() {
    }

    /**
     * Event to dynamically add unbaked models to the game, in a similar manner to data generation.
     */
    public static final Event<AddUnbaked> ADD_UNBAKED = EventFactory.createArrayBacked(AddUnbaked.class, callbacks -> modelCollector -> {
        for(AddUnbaked callback : callbacks) {
            callback.addUnbakedModels(modelCollector);
        }
    });

    @FunctionalInterface
    public interface AddUnbaked {
        /**
         * Called to add unbaked json models to the game.
         * @see net.minecraft.client.data.Model#upload(Identifier, TextureMap, BiConsumer)
         * @param modelCollector a consumer that accepts the id of a model and a supplier for it in JSON form
         */
        void addUnbakedModels(BiConsumer<Identifier, ModelSupplier> modelCollector);
    }

}
