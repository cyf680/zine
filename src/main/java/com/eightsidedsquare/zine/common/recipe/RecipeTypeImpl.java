package com.eightsidedsquare.zine.common.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;

public class RecipeTypeImpl<T extends Recipe<?>> implements RecipeType<T> {

    private final String id;

    public RecipeTypeImpl(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
