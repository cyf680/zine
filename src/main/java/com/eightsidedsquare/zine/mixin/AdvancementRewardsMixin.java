package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.advancement.AdvancementRewardsExtensions;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.loot.LootTable;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.function.LazyContainer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Optional;

@Mixin(AdvancementRewards.class)
public abstract class AdvancementRewardsMixin implements AdvancementRewardsExtensions {

    @Shadow @Final @Mutable
    private int experience;

    @Shadow @Final @Mutable
    private List<RegistryKey<LootTable>> loot;

    @Shadow @Final @Mutable
    private List<RegistryKey<Recipe<?>>> recipes;

    @Shadow @Final @Mutable
    private Optional<LazyContainer> function;

    @Override
    public void zine$setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public void zine$setLoot(List<RegistryKey<LootTable>> loot) {
        this.loot = loot;
    }

    @Override
    public void zine$addLootTable(RegistryKey<LootTable> lootTable) {
        this.loot = ZineUtil.addOrUnfreeze(this.loot, lootTable);
    }

    @Override
    public void zine$addLootTables(List<RegistryKey<LootTable>> lootTables) {
        this.loot = ZineUtil.addAllOrUnfreeze(this.loot, lootTables);
    }

    @Override
    public void zine$setRecipes(List<RegistryKey<Recipe<?>>> recipes) {
        this.recipes = recipes;
    }

    @Override
    public void zine$addRecipe(RegistryKey<Recipe<?>> recipe) {
        this.recipes = ZineUtil.addOrUnfreeze(this.recipes, recipe);
    }

    @Override
    public void zine$addRecipes(List<RegistryKey<Recipe<?>>> recipes) {
        this.recipes = ZineUtil.addAllOrUnfreeze(this.recipes, recipes);
    }

    @Override
    public void zine$setFunction(@Nullable LazyContainer function) {
        this.function = Optional.ofNullable(function);
    }
}
