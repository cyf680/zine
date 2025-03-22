package com.eightsidedsquare.zine.mixin.client;

import com.eightsidedsquare.zine.client.item.UnbakedBasicItemModelExtensions;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.client.render.item.model.BasicItemModel;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(BasicItemModel.Unbaked.class)
public abstract class UnbakedBasicItemModelMixin implements UnbakedBasicItemModelExtensions {

    @Shadow @Final @Mutable
    private List<TintSource> tints;
    @Shadow @Final @Mutable
    private Identifier model;

    @Override
    public void zine$addTint(TintSource tint) {
        this.tints = ZineUtil.addOrUnfreeze(this.tints, tint);
    }

    @Override
    public void zine$addTints(List<TintSource> tints) {
        this.tints = ZineUtil.addAllOrUnfreeze(this.tints, tints);
    }

    @Override
    public void zine$setTints(List<TintSource> tints) {
        this.tints = tints;
    }

    @Override
    public void zine$setModel(Identifier model) {
        this.model = model;
    }
}
