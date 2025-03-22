package com.eightsidedsquare.zine.mixin.client;

import com.eightsidedsquare.zine.client.language.LanguageEvents;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.resource.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

@Mixin(TranslationStorage.class)
public abstract class TranslationStorageMixin {

    @Inject(method = "load(Lnet/minecraft/resource/ResourceManager;Ljava/util/List;Z)Lnet/minecraft/client/resource/language/TranslationStorage;", at = @At(value = "NEW", target = "(Ljava/util/Map;Z)Lnet/minecraft/client/resource/language/TranslationStorage;"))
    private static void zine$invokeModifyTranslationsEvent(ResourceManager resourceManager,
                                                           List<String> definitions,
                                                           boolean rightToLeft,
                                                           CallbackInfoReturnable<TranslationStorage> cir,
                                                           @Local(ordinal = 0) Map<String, String> translations) {
        LanguageEvents.MODIFY_TRANSLATIONS.invoker().modify(translations, definitions.getLast(), rightToLeft);
    }

}
