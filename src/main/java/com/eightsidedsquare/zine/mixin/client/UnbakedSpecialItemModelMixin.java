package com.eightsidedsquare.zine.mixin.client;

import com.eightsidedsquare.zine.client.item.UnbakedSpecialItemModelExtensions;
import net.minecraft.client.render.item.model.SpecialItemModel;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SpecialItemModel.Unbaked.class)
public abstract class UnbakedSpecialItemModelMixin implements UnbakedSpecialItemModelExtensions {

    @Shadow @Final @Mutable
    private Identifier base;
    @Shadow @Final @Mutable
    private SpecialModelRenderer.Unbaked specialModel;

    @Override
    public void zine$setBase(Identifier base) {
        this.base = base;
    }

    @Override
    public void zine$setModel(SpecialModelRenderer.Unbaked model) {
        this.specialModel = model;
    }
}
