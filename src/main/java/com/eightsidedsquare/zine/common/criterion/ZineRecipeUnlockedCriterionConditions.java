package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;

public interface ZineRecipeUnlockedCriterionConditions {

    default void zine$setRecipe(RegistryKey<Recipe<?>> recipe) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
