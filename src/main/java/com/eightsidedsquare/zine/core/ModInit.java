package com.eightsidedsquare.zine.core;

import com.eightsidedsquare.zine.common.advancement.VanillaAdvancementModificationsImpl;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class ModInit implements ModInitializer {

    public static final String MOD_ID = "zine";

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        VanillaAdvancementModificationsImpl.registerEvents();
    }
}
