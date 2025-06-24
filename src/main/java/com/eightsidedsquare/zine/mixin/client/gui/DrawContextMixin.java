package com.eightsidedsquare.zine.mixin.client.gui;

import com.eightsidedsquare.zine.client.gui.ZineDrawContext;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.render.state.TextGuiElementRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DrawContext.class)
public abstract class DrawContextMixin implements ZineDrawContext {

    @Unique
    private int preparedOutlineColor;

    @Override
    public void zine$prepareOutlineColor(int outlineColor) {
        this.preparedOutlineColor = outlineColor;
    }

    @ModifyExpressionValue(method = "drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;IIIZ)V", at = @At(value = "NEW", target = "(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;Lorg/joml/Matrix3x2f;IIIIZLnet/minecraft/client/gui/ScreenRect;)Lnet/minecraft/client/gui/render/state/TextGuiElementRenderState;"))
    private TextGuiElementRenderState zine$applyOutlineColor(TextGuiElementRenderState state) {
        if(this.preparedOutlineColor != 0) {
            state.zine$setOutlineColor(this.preparedOutlineColor);
            this.preparedOutlineColor = 0;
        }
        return state;
    }
}
