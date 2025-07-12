package com.eightsidedsquare.zine.mixin.structure;

import com.eightsidedsquare.zine.common.world.structure.ZineRuleStructureProcessor;
import com.google.common.collect.ImmutableList;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(RuleStructureProcessor.class)
public abstract class RuleStructureProcessorMixin implements ZineRuleStructureProcessor {

    @Shadow @Final @Mutable
    private ImmutableList<StructureProcessorRule> rules;

    @Override
    public ImmutableList<StructureProcessorRule> zine$getRules() {
        return this.rules;
    }

    @Override
    public void zine$setRules(ImmutableList<StructureProcessorRule> rules) {
        this.rules = rules;
    }

    @Override
    public void zine$addRule(StructureProcessorRule rule) {
        this.rules = ImmutableList.<StructureProcessorRule>builder().addAll(this.rules).add(rule).build();
    }

    @Override
    public void zine$addRules(List<StructureProcessorRule> rules) {
        this.rules = ImmutableList.<StructureProcessorRule>builder().addAll(this.rules).addAll(rules).build();
    }
}
