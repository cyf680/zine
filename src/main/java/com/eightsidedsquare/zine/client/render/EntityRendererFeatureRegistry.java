package com.eightsidedsquare.zine.client.render;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

public interface EntityRendererFeatureRegistry<S extends LivingEntityRenderState, M extends EntityModel<? super S>, R extends LivingEntityRenderer<?, S, M>> {

    static <S extends LivingEntityRenderState,
            M extends EntityModel<? super S>,
            R extends LivingEntityRenderer<?, S, M>> EntityRendererFeatureRegistry<S, M, R> get(Class<R> clazz) {
        return EntityRendererFeatureRegistryImpl.get(clazz);
    }

    void addFeatures(Callback<S, M, R> callback);

    @FunctionalInterface
    interface Callback<S extends LivingEntityRenderState, M extends EntityModel<? super S>, R extends LivingEntityRenderer<?, S, M>> {

        void onFeaturesAdded(Context<S, M, R> ctx);

    }

    interface Context<S extends LivingEntityRenderState, M extends EntityModel<? super S>, R extends LivingEntityRenderer<?, S, M>> {

        void addFeature(FeatureRenderer<S, M> featureRenderer);

        R renderer();

        M model();

        ItemModelManager itemModelResolver();

        EntityRenderDispatcher dispatcher();

        TextRenderer textRenderer();

    }

}
