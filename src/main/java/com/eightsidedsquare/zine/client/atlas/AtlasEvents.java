package com.eightsidedsquare.zine.client.atlas;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.texture.atlas.AtlasSource;
import net.minecraft.util.Identifier;

import java.util.List;

@Environment(EnvType.CLIENT)
public final class AtlasEvents {

    private AtlasEvents() {
    }

    /**
     * Gets or creates an event for modifying the sources of a sprite atlas.
     * @param atlasId the id of a sprite atlas
     * @return the event for modifying the sources of the sprite atlas
     */
    public static Event<ModifySources> modifySourcesEvent(Identifier atlasId) {
        return AtlasEventsImpl.getOrCreateModifySourcesEvent(atlasId);
    }

    @FunctionalInterface
    public interface ModifySources {
        /**
         * Called to modify the sources of a sprite atlas by manipulating the sources list or sources within it.
         * @param sources the list of sprite sources for the given atlas
         */
        void modifySources(List<AtlasSource> sources);
    }

}
