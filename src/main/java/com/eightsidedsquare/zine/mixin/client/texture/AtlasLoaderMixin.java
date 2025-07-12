package com.eightsidedsquare.zine.mixin.client.texture;

import com.eightsidedsquare.zine.client.atlas.AtlasEvents;
import com.eightsidedsquare.zine.client.atlas.AtlasEventsImpl;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.texture.atlas.AtlasLoader;
import net.minecraft.client.texture.atlas.AtlasSource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AtlasLoader.class)
public abstract class AtlasLoaderMixin {

    @Inject(method = "of", at = @At(value = "NEW", target = "(Ljava/util/List;)Lnet/minecraft/client/texture/atlas/AtlasLoader;"))
    private static void zine$invokeModifySourcesEvent(ResourceManager resourceManager, Identifier id, CallbackInfoReturnable<AtlasLoader> cir, @Local List<AtlasSource> atlasSources) {
        Event<AtlasEvents.ModifySources> modifySourcesEvent = AtlasEventsImpl.getModifySourcesEvent(id);
        if(modifySourcesEvent != null) {
            modifySourcesEvent.invoker().modifySources(atlasSources);
        }
    }

}
