package com.eightsidedsquare.zine.mixin.advancement.criterion;

import com.eightsidedsquare.zine.common.criterion.ZinePlayerCriterionConditions;
import com.eightsidedsquare.zine.common.criterion.ZineRecipeCraftedCriterionConditions;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.advancement.criterion.RecipeCraftedCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Mixin(RecipeCraftedCriterion.Conditions.class)
public abstract class RecipeCraftedCriterionConditionsMixin implements ZinePlayerCriterionConditions,
        ZineRecipeCraftedCriterionConditions {

    @Shadow @Final @Mutable
    private Optional<LootContextPredicate> player;

    @Shadow @Final @Mutable
    private RegistryKey<Recipe<?>> recipeId;

    @Shadow @Final @Mutable
    private List<ItemPredicate> ingredients;

    @Override
    public void zine$setPlayer(@Nullable LootContextPredicate player) {
        this.player = Optional.ofNullable(player);
    }

    @Override
    public void zine$setRecipeId(RegistryKey<Recipe<?>> recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public void zine$setIngredients(List<ItemPredicate> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public void zine$addIngredient(ItemPredicate ingredient) {
        this.ingredients = ZineUtil.addOrUnfreeze(this.ingredients, ingredient);
    }

    @Override
    public void zine$addIngredients(Collection<ItemPredicate> ingredients) {
        this.ingredients = ZineUtil.addAllOrUnfreeze(this.ingredients, ingredients);
    }
}
