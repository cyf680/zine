package com.eightsidedsquare.zine.mixin.client.model;

import com.eightsidedsquare.zine.client.model.ModelEvents;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

@Mixin(BakedModelManager.class)
public abstract class BakedModelManagerMixin {

    @Inject(method = "method_45899", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Util;combineSafe(Ljava/util/List;)Ljava/util/concurrent/CompletableFuture;"))
    private static void zine$addUnbakedModels(Executor executor,
                                              Map<Identifier, Resource> models,
                                              CallbackInfoReturnable<CompletionStage<?>> cir,
                                              @Local List<CompletableFuture<Pair<Identifier, JsonUnbakedModel>>> list) {
        ModelEvents.ADD_UNBAKED.invoker().addUnbakedModels((id, modelSupplier) -> {
            list.add(CompletableFuture.supplyAsync(() -> {
                try {
                    JsonUnbakedModel model = JsonUnbakedModel.GSON.fromJson(modelSupplier.get(), JsonUnbakedModel.class);
                    return Pair.of(id, model);
                }catch (Exception ignored) {
                }
                return null;
            }, executor));
        });
    }

}
