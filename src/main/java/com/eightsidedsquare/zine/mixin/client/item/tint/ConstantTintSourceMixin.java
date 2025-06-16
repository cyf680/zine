package com.eightsidedsquare.zine.mixin.client.item.tint;

import com.eightsidedsquare.zine.client.item.tint.ZineConstantTintSource;
import net.minecraft.client.render.item.tint.ConstantTintSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ConstantTintSource.class)
public abstract class ConstantTintSourceMixin implements ZineConstantTintSource {

    @Shadow @Final @Mutable
    private int value;

    @Override
    public void zine$setValue(int value) {
        this.value = value;
    }
}
