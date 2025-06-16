package com.eightsidedsquare.zine.mixin.client.item.tint;

import com.eightsidedsquare.zine.client.item.tint.ZineFireworkTintSource;
import net.minecraft.client.render.item.tint.FireworkTintSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FireworkTintSource.class)
public abstract class FireworkTintSourceMixin implements ZineFireworkTintSource {

    @Shadow @Final @Mutable
    private int defaultColor;

    @Override
    public void zine$setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
    }
}
