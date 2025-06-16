package com.eightsidedsquare.zine.common.world.structure;

import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.PosRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.blockentity.RuleBlockEntityModifier;

public interface ZineStructureProcessorRule {

    default RuleTest zine$getInputPredicate() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setInputPredicate(RuleTest inputPredicate) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default RuleTest zine$getLocationPredicate() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setLocationPredicate(RuleTest locationPredicate) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default PosRuleTest zine$getPositionPredicate() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setPositionPredicate(PosRuleTest positionPredicate) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default BlockState zine$getOutputState() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setOutputState(BlockState outputState) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default RuleBlockEntityModifier zine$getBlockEntityModifier() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setBlockEntityModifier(RuleBlockEntityModifier blockEntityModifier) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
