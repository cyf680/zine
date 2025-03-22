package com.eightsidedsquare.zine.common.advancement;

import net.minecraft.loot.LootTable;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.function.LazyContainer;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface AdvancementRewardsExtensions {

    default void zine$setExperience(int experience) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setLoot(List<RegistryKey<LootTable>> loot) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addLootTable(RegistryKey<LootTable> lootTable) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addLootTables(List<RegistryKey<LootTable>> lootTables) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setRecipes(List<RegistryKey<Recipe<?>>> recipes) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addRecipe(RegistryKey<Recipe<?>> recipe) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addRecipes(List<RegistryKey<Recipe<?>>> recipes) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setFunction(@Nullable LazyContainer function) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
