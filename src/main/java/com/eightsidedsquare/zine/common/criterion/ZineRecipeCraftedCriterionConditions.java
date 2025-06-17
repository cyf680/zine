package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public interface ZineRecipeCraftedCriterionConditions {

    default void zine$setRecipeId(RegistryKey<Recipe<?>> recipeId) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setIngredients(List<ItemPredicate> ingredients) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addIngredient(ItemPredicate ingredient) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addIngredients(Collection<ItemPredicate> ingredients) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
