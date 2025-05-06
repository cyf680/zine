package com.eightsidedsquare.zinetest.mixin.client;

import com.eightsidedsquare.zinetest.TestmodClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AllayEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AllayEntityModel.class)
public abstract class AllayEntityModelMixin extends ModelMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void zinetest$init(ModelPart modelPart, CallbackInfo ci) {
        this.layerFactory = TestmodClient.CUSTOM_ENTITY;
    }

}
