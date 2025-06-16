package com.eightsidedsquare.zine.mixin.client.item.tint;

import com.eightsidedsquare.zine.client.item.tint.ZineCustomModelDataTintSource;
import net.minecraft.client.render.item.tint.CustomModelDataTintSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CustomModelDataTintSource.class)
public abstract class CustomModelDataTintSourceMixin implements ZineCustomModelDataTintSource {

    @Shadow @Final @Mutable
    private int index;

    @Shadow @Final @Mutable
    private int defaultColor;

    @Override
    public void zine$setIndex(int index) {
        this.index = index;
    }

    @Override
    public void zine$setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
    }
}
