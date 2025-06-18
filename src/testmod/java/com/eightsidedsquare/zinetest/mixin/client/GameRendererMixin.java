package com.eightsidedsquare.zinetest.mixin.client;

import com.eightsidedsquare.zinetest.core.TestmodInit;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.DefaultFramebufferSet;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.Pool;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

//    @Shadow @Final private MinecraftClient client;
//    @Shadow @Final private Pool pool;
//    @Unique
//    private static final Identifier RED_ID = TestmodInit.id("red");
//
//    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;drawEntityOutlinesFramebuffer()V", shift = At.Shift.AFTER))
//    private void test(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci) {
//        RenderSystem.resetTextureMatrix();
//        PostEffectProcessor postEffectProcessor = this.client.getShaderLoader().loadPostEffect(RED_ID, DefaultFramebufferSet.MAIN_ONLY);
//        if(postEffectProcessor != null) {
//            postEffectProcessor.render(this.client.getFramebuffer(), this.pool);
//        }
//    }
}
