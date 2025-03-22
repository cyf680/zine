package com.eightsidedsquare.zinetest.datagen;

import com.eightsidedsquare.zinetest.core.TestmodInit;
import com.eightsidedsquare.zinetest.core.TestmodItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class TestmodItemTagGen extends FabricTagProvider.ItemTagProvider {

    public TestmodItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture, null);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(TestmodItems.TOURMALINE)
                .add(Items.OBSIDIAN);
    }
}
