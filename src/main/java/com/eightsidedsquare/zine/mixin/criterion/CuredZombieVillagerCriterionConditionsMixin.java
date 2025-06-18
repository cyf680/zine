package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineCuredZombieVillagerCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZineVillagerCriterionConditions;
import net.minecraft.advancement.criterion.CuredZombieVillagerCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(CuredZombieVillagerCriterion.Conditions.class)
public abstract class CuredZombieVillagerCriterionConditionsMixin implements ZinePlayerCriterionConditions,
        ZineVillagerCriterionConditions,
        ZineCuredZombieVillagerCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> villager;

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> zombie;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }

    @Override
    public void zine$setVillager(@Nullable LootContextPredicate villager) {
        this.villager = Optional.ofNullable(villager);
    }

    @Override
    public void zine$setZombie(@Nullable LootContextPredicate zombie) {
        this.zombie = Optional.ofNullable(zombie);
    }
}
