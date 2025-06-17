package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import net.minecraft.advancement.criterion.*;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(value = {
        AnyBlockUseCriterion.Conditions.class,
        BeeNestDestroyedCriterion.Conditions.class,
        BredAnimalsCriterion.Conditions.class,
        BrewedPotionCriterion.Conditions.class,
        ChangedDimensionCriterion.Conditions.class,
        ChanneledLightningCriterion.Conditions.class,
        ConstructBeaconCriterion.Conditions.class,
        ConsumeItemCriterion.Conditions.class,
        CuredZombieVillagerCriterion.Conditions.class,
        DefaultBlockUseCriterion.Conditions.class,
        EffectsChangedCriterion.Conditions.class,
        EnchantedItemCriterion.Conditions.class,
        EnterBlockCriterion.Conditions.class,
        EntityHurtPlayerCriterion.Conditions.class,
        FallAfterExplosionCriterion.Conditions.class,
        FilledBucketCriterion.Conditions.class,
        FishingRodHookedCriterion.Conditions.class,
        InventoryChangedCriterion.Conditions.class,
        ItemCriterion.Conditions.class,
        ItemDurabilityChangedCriterion.Conditions.class,
        KilledByArrowCriterion.Conditions.class,
        LevitationCriterion.Conditions.class,
        LightningStrikeCriterion.Conditions.class,
        OnKilledCriterion.Conditions.class,
        PlayerGeneratesContainerLootCriterion.Conditions.class,
        PlayerHurtEntityCriterion.Conditions.class,
        PlayerInteractedWithEntityCriterion.Conditions.class,
        RecipeCraftedCriterion.Conditions.class,
        RecipeUnlockedCriterion.Conditions.class,
        ShotCrossbowCriterion.Conditions.class,
        SlideDownBlockCriterion.Conditions.class,
        StartedRidingCriterion.Conditions.class,
        SummonedEntityCriterion.Conditions.class,
        TameAnimalCriterion.Conditions.class,
        TargetHitCriterion.Conditions.class,
        ThrownItemPickedUpByEntityCriterion.Conditions.class,
        TickCriterion.Conditions.class,
        TravelCriterion.Conditions.class,
        UsedEnderEyeCriterion.Conditions.class,
        UsedTotemCriterion.Conditions.class,
        UsingItemCriterion.Conditions.class,
        VillagerTradeCriterion.Conditions.class
})
public abstract class PlayerCriterionConditionsMixin implements ZinePlayerCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }
}
