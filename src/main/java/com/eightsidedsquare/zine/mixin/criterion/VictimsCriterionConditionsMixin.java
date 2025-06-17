package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineVictimsCriterionConditions;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.advancement.criterion.ChanneledLightningCriterion;
import net.minecraft.advancement.criterion.KilledByArrowCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.List;

@Mixin(value = {
        ChanneledLightningCriterion.Conditions.class,
        KilledByArrowCriterion.Conditions.class
})
public abstract class VictimsCriterionConditionsMixin implements ZineVictimsCriterionConditions {

    @Shadow(remap = false) @Final @Mutable
    private List<LootContextPredicate> victims;

    @Override
    public void zine$setVictims(List<LootContextPredicate> victims) {
        this.victims = victims;
    }

    @Override
    public void zine$addVictim(LootContextPredicate victim) {
        this.victims = ZineUtil.addOrUnfreeze(this.victims, victim);
    }

    @Override
    public void zine$addVictims(Collection<LootContextPredicate> victims) {
        this.victims = ZineUtil.addAllOrUnfreeze(this.victims, victims);
    }

}
