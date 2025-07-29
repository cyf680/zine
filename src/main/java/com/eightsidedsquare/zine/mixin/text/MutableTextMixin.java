package com.eightsidedsquare.zine.mixin.text;

import com.eightsidedsquare.zine.common.text.ZineMutableText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MutableText.class)
public abstract class MutableTextMixin implements Text, ZineMutableText {

    @Unique
    private boolean frozen = false;

    @Unique
    private MutableText cast() {
        return (MutableText) (Object) this;
    }

    @Override
    public MutableText zine$freeze() {
        this.frozen = true;
        return this.cast();
    }

    @Override
    public MutableText zine$unfreeze() {
        this.frozen = false;
        return this.cast();
    }

    @Inject(method = "setStyle", at = @At("HEAD"), cancellable = true)
    private void zine$cancelSetStyleIfFrozen(Style style, CallbackInfoReturnable<MutableText> cir) {
        if(this.frozen) {
            cir.setReturnValue(this.cast());
        }
    }

    @Inject(method = "append(Lnet/minecraft/text/Text;)Lnet/minecraft/text/MutableText;", at = @At("HEAD"), cancellable = true)
    private void zine$cancelAppendIfFrozen(Text text, CallbackInfoReturnable<MutableText> cir) {
        if(this.frozen) {
            cir.setReturnValue(this.cast());
        }
    }
}
