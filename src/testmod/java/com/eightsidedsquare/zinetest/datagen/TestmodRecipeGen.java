package com.eightsidedsquare.zinetest.datagen;

import com.eightsidedsquare.zinetest.core.TestmodItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class TestmodRecipeGen extends RecipeGenerator {

    protected TestmodRecipeGen(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    @Override
    public void generate() {
        this.offerSmithingTrimRecipe(
                TestmodItems.CHECKERED_ARMOR_TRIM_SMITHING_TEMPLATE,
                RegistryKey.of(RegistryKeys.RECIPE, Registries.ITEM.getId(TestmodItems.CHECKERED_ARMOR_TRIM_SMITHING_TEMPLATE).withSuffixedPath("_smithing_trim"))
        );
    }

    protected static class Provider extends FabricRecipeProvider {

        protected Provider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
            return new TestmodRecipeGen(wrapperLookup, recipeExporter);
        }

        @Override
        public String getName() {
            return "Recipes";
        }
    }
}
