package com.eightsidedsquare.zine.mixin.client;

import com.eightsidedsquare.zine.client.item.UnbakedCompositeItemModelExtensions;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.client.render.item.model.CompositeItemModel;
import net.minecraft.client.render.item.model.ItemModel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(CompositeItemModel.Unbaked.class)
public abstract class UnbakedCompositeItemModelMixin implements UnbakedCompositeItemModelExtensions {

    @Shadow @Final @Mutable
    private List<ItemModel.Unbaked> models;

    @Override
    public void zine$addModel(ItemModel.Unbaked model) {
        this.models = ZineUtil.addOrUnfreeze(this.models, model);
    }

    @Override
    public void zine$addModels(List<ItemModel.Unbaked> models) {
        this.models = ZineUtil.addAllOrUnfreeze(this.models, models);
    }

    @Override
    public void zine$setModels(List<ItemModel.Unbaked> models) {
        this.models = models;
    }
}
