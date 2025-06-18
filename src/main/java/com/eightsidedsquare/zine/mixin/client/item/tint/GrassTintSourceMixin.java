package com.eightsidedsquare.zine.mixin.client.item.tint;

import com.eightsidedsquare.zine.client.item.tint.ZineGrassTintSource;
import net.minecraft.client.render.item.tint.GrassTintSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GrassTintSource.class)
public abstract class GrassTintSourceMixin implements ZineGrassTintSource {

    @Shadow @Final @Mutable
    private float downfall;

    @Shadow @Final @Mutable
    private float temperature;

    @Override
    public void zine$setTemperature(float temperature) {
        this.temperature = temperature;
    }

    @Override
    public void zine$setDownfall(float downfall) {
        this.downfall = downfall;
    }
}
