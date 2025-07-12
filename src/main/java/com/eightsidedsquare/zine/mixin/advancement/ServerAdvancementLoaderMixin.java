package com.eightsidedsquare.zine.mixin.advancement;

import com.eightsidedsquare.zine.common.advancement.AdvancementEvents;
import com.eightsidedsquare.zine.common.advancement.AdvancementEventsImpl;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.advancement.Advancement;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ServerAdvancementLoader.class)
public abstract class ServerAdvancementLoaderMixin {

    @Shadow @Final private RegistryWrapper.WrapperLookup registries;

    @ModifyVariable(method = "method_20723", at = @At("HEAD"), argsOnly = true)
    private Advancement zine$modifyAdvancements(Advancement advancement, @Local(argsOnly = true) Identifier id) {
        Event<AdvancementEvents.ModifyAdvancement> event = AdvancementEventsImpl.getModifyAdvancementEvent(id);
        if(event != null) {
            return event.invoker().modifyAdvancement(advancement, this.registries);
        }
        return advancement;
    }

}
