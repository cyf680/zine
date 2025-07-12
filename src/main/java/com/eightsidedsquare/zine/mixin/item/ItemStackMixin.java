package com.eightsidedsquare.zine.mixin.item;

import com.eightsidedsquare.zine.core.ZineDataComponentTypes;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.component.ComponentHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ComponentHolder {

    @WrapOperation(method = "toHoverableText", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;", ordinal = 1))
    private MutableText zine$applyTextColorToHoverable(MutableText text, Formatting formatting, Operation<MutableText> original) {
        return this.zine$applyTextColor(text, formatting, original);
    }

    @WrapOperation(method = "getFormattedName", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;", ordinal = 0))
    private MutableText zine$applyTextColorToName(MutableText text, Formatting formatting, Operation<MutableText> original) {
        return this.zine$applyTextColor(text, formatting, original);
    }

    @Unique
    private MutableText zine$applyTextColor(MutableText text, Formatting formatting, Operation<MutableText> original) {
        if(this.contains(ZineDataComponentTypes.ITEM_NAME_COLOR)) {
            return text.withColor(this.getOrDefault(ZineDataComponentTypes.ITEM_NAME_COLOR, -1));
        }else {
            return original.call(text, formatting);
        }
    }

}
