package com.eightsidedsquare.zine.mixin.predicate;

import com.eightsidedsquare.zine.common.predicate.ZineLootContextPredicate;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.function.Predicate;

@Mixin(LootContextPredicate.class)
public abstract class LootContextPredicateMixin implements ZineLootContextPredicate {

    @Shadow @Final @Mutable
    private List<LootCondition> conditions;

    @Shadow @Final @Mutable
    private Predicate<LootContext> combinedCondition;

    @Override
    public List<LootCondition> zine$getConditions() {
        return this.conditions;
    }

    @Override
    public void zine$setConditions(List<LootCondition> conditions) {
        this.conditions = conditions;
        this.combinedCondition = Util.allOf(conditions);
    }

    @Override
    public void zine$addCondition(LootCondition condition) {
        this.conditions = ZineUtil.addOrUnfreeze(this.conditions, condition);
        this.combinedCondition = Util.allOf(this.conditions);
    }

    @Override
    public void zine$addConditions(List<LootCondition> conditions) {
        this.conditions = ZineUtil.addAllOrUnfreeze(this.conditions, conditions);
        this.combinedCondition = Util.allOf(this.conditions);
    }
}
