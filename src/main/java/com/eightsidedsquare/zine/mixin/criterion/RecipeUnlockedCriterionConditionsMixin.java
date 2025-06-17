package com.eightsidedsquare.zine.mixin.criterion;

import com.eightsidedsquare.zine.common.criterion.ZineRecipeUnlockedCriterionConditions;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(RecipeUnlockedCriterion.Conditions.class)
public abstract class RecipeUnlockedCriterionConditionsMixin implements ZineRecipeUnlockedCriterionConditions {

    @Shadow @Final @Mutable
    private RegistryKey<Recipe<?>> recipe;

    @Override
    public void zine$setRecipe(RegistryKey<Recipe<?>> recipe) {
        this.recipe = recipe;
    }
}
