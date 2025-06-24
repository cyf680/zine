package com.eightsidedsquare.zine.mixin.client.font;

import com.eightsidedsquare.zine.client.font.ZineTextRenderer;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.font.TextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TextRenderer.class)
public abstract class TextRendererMixin implements ZineTextRenderer {

    @Unique
    private int preparedOutlineColor;

    @Override
    public void zine$prepareOutlineColor(int outlineColor) {
        this.preparedOutlineColor = outlineColor;
    }

    @ModifyExpressionValue(method = {"prepare(Ljava/lang/String;FFIZI)Lnet/minecraft/client/font/TextRenderer$GlyphDrawable;", "prepare(Lnet/minecraft/text/OrderedText;FFIZI)Lnet/minecraft/client/font/TextRenderer$GlyphDrawable;"}, at = @At(value = "NEW", target = "(Lnet/minecraft/client/font/TextRenderer;FFIIZ)Lnet/minecraft/client/font/TextRenderer$Drawer;"))
    private TextRenderer.Drawer zine$applyOutlineColor(TextRenderer.Drawer drawer) {
        if(this.preparedOutlineColor != 0) {
            drawer.zine$setOutlineColor(this.preparedOutlineColor);
            this.preparedOutlineColor = 0;
        }
        return drawer;
    }
}
