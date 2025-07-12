package com.eightsidedsquare.zine.mixin.client.render;

import com.eightsidedsquare.zine.client.render.EntityRendererFeatureRegistryImpl;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityRenderers.class)
public abstract class EntityRenderersMixin {

    @ModifyExpressionValue(method = {"method_32174", "method_32175"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRendererFactory;create(Lnet/minecraft/client/render/entity/EntityRendererFactory$Context;)Lnet/minecraft/client/render/entity/EntityRenderer;"))
    private static EntityRenderer<Entity, ?> zine$modifyRenderer(EntityRenderer<Entity, ?> renderer) {
        EntityRendererFeatureRegistryImpl.apply(renderer);
        return renderer;
    }

}
