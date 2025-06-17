package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineVillagerCriterionConditions;
import net.minecraft.advancement.criterion.CuredZombieVillagerCriterion;
import net.minecraft.advancement.criterion.VillagerTradeCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(value = {
        CuredZombieVillagerCriterion.Conditions.class,
        VillagerTradeCriterion.Conditions.class
})
public abstract class VillagerCriterionConditionsMixin implements ZineVillagerCriterionConditions {

    @Shadow(remap = false) @Final @Mutable
    private Optional<LootContextPredicate> villager;

    @Override
    public void zine$setVillager(@Nullable LootContextPredicate villager) {
        this.villager = Optional.ofNullable(villager);
    }
}
