package com.eightsidedsquare.zine.client;

import com.eightsidedsquare.zine.client.atlas.AtlasEvents;
import com.eightsidedsquare.zine.client.atlas.ConnectedTexturesAtlasSource;
import com.eightsidedsquare.zine.client.atlas.GeneratorAtlasSource;
import com.eightsidedsquare.zine.client.atlas.RemapAtlasSource;
import com.eightsidedsquare.zine.client.atlas.generator.SpriteGenerator;
import com.eightsidedsquare.zine.client.atlas.gradient.Gradient;
import com.eightsidedsquare.zine.client.item.ItemModelEvents;
import com.eightsidedsquare.zine.client.model.ModelEvents;
import com.eightsidedsquare.zine.client.trim.ArmorTrimRegistryImpl;
import com.eightsidedsquare.zine.core.ModInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.AtlasSourceTypeRegistry;
import net.minecraft.util.Identifier;

public class ModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        this.callBootstraps();

        this.registerEvents();

        AtlasSourceTypeRegistry.register(ModInit.id("generator"), GeneratorAtlasSource.TYPE);
        AtlasSourceTypeRegistry.register(ModInit.id("remap"), RemapAtlasSource.TYPE);
        AtlasSourceTypeRegistry.register(ModInit.id("connected_textures"), ConnectedTexturesAtlasSource.TYPE);
    }

    private void callBootstraps() {
        Gradient.bootstrap();
        SpriteGenerator.bootstrap();
    }

    private void registerEvents() {
        AtlasEvents.modifySourcesEvent(Identifier.ofVanilla("blocks")).register(ArmorTrimRegistryImpl::modifyBlocksAtlas);
        AtlasEvents.modifySourcesEvent(Identifier.ofVanilla("armor_trims")).register(ArmorTrimRegistryImpl::modifyArmorTrimsAtlas);
        ModelEvents.ADD_UNBAKED.register(ArmorTrimRegistryImpl::addUnbakedModels);
        ItemModelEvents.BEFORE_BAKE.register(ArmorTrimRegistryImpl::modifyItemModels);
    }
}
