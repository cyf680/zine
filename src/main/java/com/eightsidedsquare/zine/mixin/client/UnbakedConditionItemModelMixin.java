package com.eightsidedsquare.zine.mixin.client;

import com.eightsidedsquare.zine.client.item.UnbakedConditionItemModelExtensions;
import net.minecraft.client.render.item.model.ConditionItemModel;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.property.bool.BooleanProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ConditionItemModel.Unbaked.class)
public abstract class UnbakedConditionItemModelMixin implements UnbakedConditionItemModelExtensions {

    @Shadow @Final @Mutable
    private BooleanProperty property;
    @Shadow @Final @Mutable
    private ItemModel.Unbaked onTrue;
    @Shadow @Final @Mutable
    private ItemModel.Unbaked onFalse;

    @Override
    public void zine$setProperty(BooleanProperty property) {
        this.property = property;
    }

    @Override
    public void zine$setTrueModel(ItemModel.Unbaked model) {
        this.onTrue = model;
    }

    @Override
    public void zine$setFalseModel(ItemModel.Unbaked model) {
        this.onFalse = model;
    }
}
