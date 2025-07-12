package com.eightsidedsquare.zine.mixin.advancement.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineKilledByArrowCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZineVictimsCriterionConditions;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.advancement.criterion.KilledByArrowCriterion;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Mixin(KilledByArrowCriterion.Conditions.class)
public abstract class KilledByArrowCriterionConditionsMixin implements ZinePlayerCriterionConditions,
        ZineVictimsCriterionConditions,
        ZineKilledByArrowCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Shadow @Final @Mutable
    private List<LootContextPredicate> victims;

    @Shadow @Final @Mutable
    private NumberRange.IntRange uniqueEntityTypes;

    @Shadow @Final @Mutable
    private Optional<ItemPredicate> firedFromWeapon;

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

    @Override
    public void zine$setUniqueEntityTypes(NumberRange.IntRange uniqueEntityTypes) {
        this.uniqueEntityTypes = uniqueEntityTypes;
    }

    @Override
    public void zine$setFiredFromWeapon(@Nullable ItemPredicate firedFromWeapon) {
        this.firedFromWeapon = Optional.ofNullable(firedFromWeapon);
    }
}
