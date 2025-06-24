package com.eightsidedsquare.zine.mixin.client.font;

import com.eightsidedsquare.zine.client.font.ZineOutlinable;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.font.BakedGlyph;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BakedGlyph.DrawnGlyph.class)
public abstract class BakedGlyphDrawnGlyphMixin implements ZineOutlinable {

    @Unique
    private int outlineColor;

    @Override
    public int zine$getOutlineColor() {
        return this.outlineColor;
    }

    @Override
    public void zine$setOutlineColor(int outlineColor) {
        this.outlineColor = outlineColor;
    }

    @ModifyReturnValue(method = "hasShadow", at = @At("RETURN"))
    private boolean zine$noShadowWhenOutline(boolean original) {
        return original && !this.zine$hasOutline();
    }
}
