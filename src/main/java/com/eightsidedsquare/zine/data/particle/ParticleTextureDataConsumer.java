package com.eightsidedsquare.zine.data.particle;

import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;

public interface ParticleTextureDataConsumer {

    void accept(ParticleType<?> particleType, List<Identifier> textures);

    default void accept(ParticleType<?> particleType, Identifier... textures) {
        this.accept(particleType, List.of(textures));
    }

    /**
     * Accepts the particle type with its registered ID as its single texture
     */
    default void accept(ParticleType<?> particleType) {
        this.accept(particleType, Registries.PARTICLE_TYPE.getId(particleType));
    }

}
