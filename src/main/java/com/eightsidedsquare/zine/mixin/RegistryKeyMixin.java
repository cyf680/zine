package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.registry.ZineRegistryKey;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RegistryKey.class)
public abstract class RegistryKeyMixin<T> implements ZineRegistryKey<T> {

    @Shadow @Final private Identifier registry;

    @Shadow @Final private Identifier value;

    @Override
    public String zine$getTranslationKey() {
        return Util.createTranslationKey(this.registry.getPath(), this.value);
    }

    @Override
    public MutableText zine$getName() {
        return Text.translatable(this.zine$getTranslationKey());
    }
}
