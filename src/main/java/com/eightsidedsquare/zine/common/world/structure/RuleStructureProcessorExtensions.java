package com.eightsidedsquare.zine.common.world.structure;

import com.google.common.collect.ImmutableList;
import net.minecraft.structure.processor.StructureProcessorRule;

import java.util.List;

public interface RuleStructureProcessorExtensions {

    default ImmutableList<StructureProcessorRule> zine$getRules() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setRules(ImmutableList<StructureProcessorRule> rules) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addRule(StructureProcessorRule rule) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addRules(List<StructureProcessorRule> rules) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
