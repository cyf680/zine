package com.eightsidedsquare.zine.mixin.client;

import com.eightsidedsquare.zine.client.block.BlockStateDefinitionEvents;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.model.BlockStatesLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.ModelVariantMap;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.registry.Registries;
import net.minecraft.resource.Resource;
import net.minecraft.state.StateManager;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.Function;

@Mixin(BlockStatesLoader.class)
public abstract class BlockStatesLoaderMixin {

    @Shadow @Final private static Logger LOGGER;

    @Inject(method = "method_65721", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Util;combineSafe(Ljava/util/List;)Ljava/util/concurrent/CompletableFuture;"))
    private static void zine$addBlockStateModels(Function<Identifier, StateManager<Block, BlockState>> idToStatesConverter,
                                                 UnbakedModel unbakedModel,
                                                 Executor executor,
                                                 Map<Identifier, List<Resource>> resources,
                                                 CallbackInfoReturnable<CompletionStage<?>> cir,
                                                 @Local(ordinal = 0) List<CompletableFuture<BlockStatesLoader.BlockStateDefinition>> list) {
        BlockStateDefinitionEvents.ADD_DEFINITIONS.invoker().addBlockStateDefinitions(blockStateSupplier -> {
            list.add(CompletableFuture.supplyAsync(() -> {
                Map<ModelIdentifier, BlockStatesLoader.BlockModel> map = new HashMap<>();
                Identifier id = Registries.BLOCK.getId(blockStateSupplier.getBlock());
                StateManager<Block, BlockState> stateManager = idToStatesConverter.apply(id);
                try {
                    ModelVariantMap.fromJson(blockStateSupplier.get())
                            .parse(stateManager, id + "/add_block_state_models_event")
                            .forEach((state, model) -> {
                                ModelIdentifier modelIdentifier = BlockModels.getModelId(id, state);
                                map.put(modelIdentifier, new BlockStatesLoader.BlockModel(state, model));
                            });
                } catch (Exception e) {
                    LOGGER.error("Failed to load blockstate definition {} from event", id, e);
                    return null;
                }
                return new BlockStatesLoader.BlockStateDefinition(map);
            }));
        });
    }

}
