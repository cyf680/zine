package com.eightsidedsquare.zine.common.criterion;

import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;

public interface ZineRecipeUnlockedCriterionConditions {

    default void zine$setRecipe(RegistryKey<Recipe<?>> recipe) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
