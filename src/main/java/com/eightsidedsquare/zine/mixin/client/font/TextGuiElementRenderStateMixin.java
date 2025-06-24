package com.eightsidedsquare.zine.mixin.client.font;

import com.eightsidedsquare.zine.client.font.ZineOutlinable;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.render.state.TextGuiElementRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TextGuiElementRenderState.class)
public abstract class TextGuiElementRenderStateMixin implements ZineOutlinable {

    @Shadow @Final
    public TextRenderer textRenderer;

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

    @Inject(method = "prepare", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;prepare(Lnet/minecraft/text/OrderedText;FFIZI)Lnet/minecraft/client/font/TextRenderer$GlyphDrawable;"))
    private void zine$applyOutlineColor(CallbackInfoReturnable<TextRenderer.GlyphDrawable> cir) {
        if(this.zine$hasOutline()) {
            this.textRenderer.zine$prepareOutlineColor(this.outlineColor);
        }
    }
}
