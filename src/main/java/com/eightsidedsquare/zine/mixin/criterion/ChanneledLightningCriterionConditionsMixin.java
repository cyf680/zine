package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZineVictimsCriterionConditions;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.advancement.criterion.ChanneledLightningCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Mixin(ChanneledLightningCriterion.Conditions.class)
public abstract class ChanneledLightningCriterionConditionsMixin implements ZinePlayerCriterionConditions,
        ZineVictimsCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Shadow @Final @Mutable
    private List<LootContextPredicate> victims;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }

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
