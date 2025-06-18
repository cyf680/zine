package com.eightsidedsquare.zine.common.entity.spawn;

import net.minecraft.predicate.NumberRange;

public interface ZineMoonBrightnessSpawnCondition {

    default void zine$setRange(NumberRange.DoubleRange range) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
