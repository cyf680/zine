package com.eightsidedsquare.zine.mixin.client;

import com.eightsidedsquare.zine.core.ZineDataComponentTypes;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow private ItemStack currentStack;

    @WrapOperation(method = "renderHeldItemTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;", ordinal = 0))
    private MutableText zine$applyNameColor(MutableText text, Formatting formatting, Operation<MutableText> original) {
        if(this.currentStack.contains(ZineDataComponentTypes.ITEM_NAME_COLOR)) {
            return text.withColor(this.currentStack.getOrDefault(ZineDataComponentTypes.ITEM_NAME_COLOR, -1));
        }else {
            return original.call(text, formatting);
        }
    }

}
