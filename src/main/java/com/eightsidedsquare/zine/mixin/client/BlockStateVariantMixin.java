package com.eightsidedsquare.zine.mixin.client;

import com.eightsidedsquare.zine.client.block.BlockStateVariantExtensions;
import net.minecraft.client.data.BlockStateVariant;
import net.minecraft.client.data.VariantSetting;
import net.minecraft.client.data.VariantSettings;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

@Mixin(BlockStateVariant.class)
public abstract class BlockStateVariantMixin implements BlockStateVariantExtensions {

    @Shadow public abstract <T> BlockStateVariant put(VariantSetting<T> key, T value);

    @Shadow @Final private Map<VariantSetting<?>, VariantSetting<?>.Value> properties;

    @Override
    public BlockStateVariant zine$model(Identifier model) {
        return this.put(VariantSettings.MODEL, model);
    }

    @Unique
    private <T> BlockStateVariant zine$putOrRemove(boolean predicate, VariantSetting<T> variantSetting, T value) {
        if(predicate) {
            return this.put(variantSetting, value);
        }
        this.properties.remove(variantSetting);
        return (BlockStateVariant) (Object) this;
    }

    @Override
    public BlockStateVariant zine$x(VariantSettings.Rotation rotation) {
        return this.zine$putOrRemove(rotation != VariantSettings.Rotation.R0, VariantSettings.X, rotation);
    }

    @Override
    public BlockStateVariant zine$y(VariantSettings.Rotation rotation) {
        return this.zine$putOrRemove(rotation != VariantSettings.Rotation.R0, VariantSettings.Y, rotation);
    }

    @Override
    public BlockStateVariant zine$uvlock(boolean uvlock) {
        return this.zine$putOrRemove(uvlock, VariantSettings.UVLOCK, uvlock);
    }

    @Override
    public BlockStateVariant zine$weight(int weight) {
        return this.zine$putOrRemove(weight > 0, VariantSettings.WEIGHT, weight);
    }
}
