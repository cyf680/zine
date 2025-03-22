package com.eightsidedsquare.zine.mixin.client;

import com.eightsidedsquare.zine.client.item.ItemModelEvents;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.item.ItemAsset;
import net.minecraft.client.item.ItemAssetsLoader;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

@Mixin(ItemAssetsLoader.class)
public abstract class ItemAssetsLoaderMixin {

    @Inject(method = "method_65930", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private static void zine$modifyUnbakedItemModels(List<ItemAssetsLoader.Definition> definitions, CallbackInfoReturnable<ItemAssetsLoader.Result> cir, @Local ItemAssetsLoader.Definition definition) {
        ItemAsset itemAsset = definition.clientItemInfo();
        if(itemAsset == null) {
            return;
        }
        itemAsset.model = ItemModelEvents.BEFORE_BAKE.invoker().modifyBeforeBake(definition.id(), itemAsset.model);
    }

    @Inject(method = "method_65930", at = @At(value = "NEW", target = "(Ljava/util/Map;)Lnet/minecraft/client/item/ItemAssetsLoader$Result;"))
    private static void zine$addUnbakedItemModels(List<ItemAssetsLoader.Definition> definitions, CallbackInfoReturnable<ItemAssetsLoader.Result> cir, @Local Map<Identifier, ItemAsset> map) {
        ItemModelEvents.ADD_UNBAKED.invoker().addUnbakedModels(map::put);
    }

}
