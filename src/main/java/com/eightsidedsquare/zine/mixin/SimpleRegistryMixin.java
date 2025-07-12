package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.registry.FreezeRegistriesEventsImpl;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.SimpleRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SimpleRegistry.class)
public abstract class SimpleRegistryMixin implements MutableRegistry<Object> {

    @Inject(method = "freeze", at = @At(value = "FIELD", target = "Lnet/minecraft/registry/SimpleRegistry;frozen:Z", ordinal = 1))
    private void zine$beforeFreeze(CallbackInfoReturnable<Registry<?>> cir) {
        FreezeRegistriesEventsImpl.apply(true, this);
    }

    @Inject(method = "freeze", at = @At(value = "RETURN", ordinal = 1))
    private void zine$afterFreeze(CallbackInfoReturnable<Registry<?>> cir) {
        FreezeRegistriesEventsImpl.apply(false, this);
    }

}
