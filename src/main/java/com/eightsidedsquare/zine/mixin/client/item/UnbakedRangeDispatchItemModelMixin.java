package com.eightsidedsquare.zine.mixin.client.item;

import com.eightsidedsquare.zine.client.item.ZineUnbakedRangeDispatchItemModel;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.RangeDispatchItemModel;
import net.minecraft.client.render.item.property.numeric.NumericProperty;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Optional;

@Mixin(RangeDispatchItemModel.Unbaked.class)
public abstract class UnbakedRangeDispatchItemModelMixin implements ZineUnbakedRangeDispatchItemModel {

    @Shadow @Final @Mutable
    private NumericProperty property;
    @Shadow @Final @Mutable
    private float scale;
    @Shadow @Final @Mutable
    private List<RangeDispatchItemModel.Entry> entries;
    @Shadow @Final @Mutable
    private Optional<ItemModel.Unbaked> fallback;

    @Override
    public void zine$setProperty(NumericProperty property) {
        this.property = property;
    }

    @Override
    public void zine$setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public void zine$setFallback(@Nullable ItemModel.Unbaked fallback) {
        this.fallback = Optional.ofNullable(fallback);
    }

    @Override
    public void zine$addEntry(RangeDispatchItemModel.Entry entry) {
        this.entries = ZineUtil.addOrUnfreeze(this.entries, entry);
    }

    @Override
    public void zine$addEntries(List<RangeDispatchItemModel.Entry> entries) {
        this.entries = ZineUtil.addAllOrUnfreeze(this.entries, entries);
    }
}
