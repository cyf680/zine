package com.eightsidedsquare.zine.mixin.structure;

import com.eightsidedsquare.zine.common.world.structure.ZineStructureProcessorRule;
import net.minecraft.block.BlockState;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.PosRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.blockentity.RuleBlockEntityModifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StructureProcessorRule.class)
public abstract class StructureProcessorRuleMixin implements ZineStructureProcessorRule {

    @Shadow @Final @Mutable
    private RuleTest inputPredicate;

    @Shadow @Final @Mutable
    private RuleTest locationPredicate;

    @Shadow @Final @Mutable
    private PosRuleTest positionPredicate;

    @Shadow @Final @Mutable
    private BlockState outputState;

    @Shadow @Final @Mutable
    private RuleBlockEntityModifier blockEntityModifier;

    @Override
    public RuleTest zine$getInputPredicate() {
        return this.inputPredicate;
    }

    @Override
    public void zine$setInputPredicate(RuleTest inputPredicate) {
        this.inputPredicate = inputPredicate;
    }

    @Override
    public RuleTest zine$getLocationPredicate() {
        return this.locationPredicate;
    }

    @Override
    public void zine$setLocationPredicate(RuleTest locationPredicate) {
        this.locationPredicate = locationPredicate;
    }

    @Override
    public PosRuleTest zine$getPositionPredicate() {
        return this.positionPredicate;
    }

    @Override
    public void zine$setPositionPredicate(PosRuleTest positionPredicate) {
        this.positionPredicate = positionPredicate;
    }

    @Override
    public BlockState zine$getOutputState() {
        return this.outputState;
    }

    @Override
    public void zine$setOutputState(BlockState outputState) {
        this.outputState = outputState;
    }

    @Override
    public RuleBlockEntityModifier zine$getBlockEntityModifier() {
        return this.blockEntityModifier;
    }

    @Override
    public void zine$setBlockEntityModifier(RuleBlockEntityModifier blockEntityModifier) {
        this.blockEntityModifier = blockEntityModifier;
    }
}
