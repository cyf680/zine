package com.eightsidedsquare.zine.client.render;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

import java.util.List;
import java.util.Map;

public final class EntityRendererFeatureRegistryImpl {

    private static final Map<Class<?>, Entry<?, ?, ?>> CALLBACKS = new Object2ObjectOpenHashMap<>();

    public static <S extends LivingEntityRenderState,
            M extends EntityModel<? super S>,
            R extends LivingEntityRenderer<?, S, M>> EntityRendererFeatureRegistry<S, M, R> get(Class<R> clazz) {
        return callback -> getOrCreateEntry(clazz).callbacks.add(callback);
    }

    @SuppressWarnings("unchecked")
    private static <S extends LivingEntityRenderState,
            M extends EntityModel<? super S>,
            R extends LivingEntityRenderer<?, S, M>> Entry<S, M, R> getOrCreateEntry(Class<R> clazz) {
        return (Entry<S, M, R>) CALLBACKS.computeIfAbsent(clazz, ignoredClass -> new Entry<>(clazz));
    }

    public static void apply(EntityRenderer<?, ?> renderer) {
        if(renderer instanceof LivingEntityRenderer<?, ?, ?> livingEntityRenderer) {
            CALLBACKS.values().forEach(entry -> entry.apply(livingEntityRenderer));
        }
    }

    private record ContextImpl<S extends LivingEntityRenderState, M extends EntityModel<? super S>, R extends LivingEntityRenderer<?, S, M>>(R renderer)
                implements EntityRendererFeatureRegistry.Context<S, M, R> {

        @Override
            public void addFeature(FeatureRenderer<S, M> featureRenderer) {
                this.renderer.addFeature(featureRenderer);
            }

            @Override
            public M model() {
                return this.renderer.getModel();
            }

            @Override
            public ItemModelManager itemModelResolver() {
                return this.renderer.itemModelResolver;
            }

            @Override
            public EntityRenderDispatcher dispatcher() {
                return this.renderer.dispatcher;
            }

            @Override
            public TextRenderer textRenderer() {
                return this.renderer.getTextRenderer();
            }
        }

    private record Entry<S extends LivingEntityRenderState, M extends EntityModel<? super S>, R extends LivingEntityRenderer<?, S, M>>(
            Class<R> clazz, List<EntityRendererFeatureRegistry.Callback<S, M, R>> callbacks) {

        private Entry(Class<R> clazz) {
            this(clazz, new ObjectArrayList<>());
        }

        @SuppressWarnings("unchecked")
        private void apply(LivingEntityRenderer<?, ?, ?> renderer) {
            if(this.clazz.isAssignableFrom(renderer.getClass())) {
                EntityRendererFeatureRegistry.Context<S, M, R> context = new ContextImpl<>((R) renderer);
                this.callbacks.forEach(callback -> callback.onFeaturesAdded(context));
            }
        }
    }
}
