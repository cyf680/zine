package com.eightsidedsquare.zinetest.mixin.client;

import net.minecraft.client.render.entity.model.AllayEntityModel;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AllayEntityModel.class)
public abstract class AllayEntityModelMixin extends ModelMixin {

//    @Inject(method = "<init>", at = @At("TAIL"))
//    private void zinetest$init(ModelPart modelPart, CallbackInfo ci) {
//        this.layerFactory = TestmodClient.CUSTOM_ENTITY;
//    }

}
