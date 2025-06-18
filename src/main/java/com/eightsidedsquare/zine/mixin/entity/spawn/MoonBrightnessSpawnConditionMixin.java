package com.eightsidedsquare.zine.mixin.entity.spawn;

import com.eightsidedsquare.zine.common.entity.spawn.ZineMoonBrightnessSpawnCondition;
import net.minecraft.entity.spawn.MoonBrightnessSpawnCondition;
import net.minecraft.predicate.NumberRange;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MoonBrightnessSpawnCondition.class)
public abstract class MoonBrightnessSpawnConditionMixin implements ZineMoonBrightnessSpawnCondition {

    @Shadow @Final @Mutable
    private NumberRange.DoubleRange range;

    @Override
    public void zine$setRange(NumberRange.DoubleRange range) {
        this.range = range;
    }
}
