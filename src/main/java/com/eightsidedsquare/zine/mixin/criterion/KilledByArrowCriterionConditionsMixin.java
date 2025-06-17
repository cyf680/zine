package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineKilledByArrowCriterionConditions;
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
public abstract class KilledByArrowCriterionConditionsMixin implements ZineKilledByArrowCriterionConditions {

    @Shadow @Final @Mutable
    private NumberRange.IntRange uniqueEntityTypes;

    @Shadow @Final @Mutable
    private Optional<ItemPredicate> firedFromWeapon;

    @Override
    public void zine$setUniqueEntityTypes(NumberRange.IntRange uniqueEntityTypes) {
        this.uniqueEntityTypes = uniqueEntityTypes;
    }

    @Override
    public void zine$setFiredFromWeapon(@Nullable ItemPredicate firedFromWeapon) {
        this.firedFromWeapon = Optional.ofNullable(firedFromWeapon);
    }
}
