package com.eightsidedsquare.zine.client.registry;

import net.minecraft.util.Identifier;

public class ClientRegistryHelperImpl implements ClientRegistryHelper {

    private final String namespace;

    ClientRegistryHelperImpl(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public Identifier id(String name) {
        return Identifier.of(this.namespace, name);
    }
}
