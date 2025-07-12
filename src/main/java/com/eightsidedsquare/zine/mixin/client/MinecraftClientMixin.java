package com.eightsidedsquare.zine.mixin.client;

import com.eightsidedsquare.zine.client.util.ZineMinecraftClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin implements ZineMinecraftClient {

    @Shadow @Final private RenderTickCounter.Dynamic renderTickCounter;

    @Override
    public float zine$getTickProgress(boolean ignoreFreeze) {
        return this.renderTickCounter.getTickProgress(ignoreFreeze);
    }

    @Override
    public float zine$getFixedDeltaTicks() {
        return this.renderTickCounter.getFixedDeltaTicks();
    }

    @Override
    public float zine$getDynamicDeltaTicks() {
        return this.renderTickCounter.getDynamicDeltaTicks();
    }
}
