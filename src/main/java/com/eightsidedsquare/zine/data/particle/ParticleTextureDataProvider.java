package com.eightsidedsquare.zine.data.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Extend this class and implement {@link ParticleTextureDataProvider#generate}.
 *
 * <p>Register an instance of the class with {@link FabricDataGenerator.Pack#addProvider} in a {@link net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint}.
 */
public abstract class ParticleTextureDataProvider implements DataProvider {

    private final DataOutput.PathResolver pathResolver;

    public ParticleTextureDataProvider(FabricDataOutput output) {
        this.pathResolver = output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "particles");
    }

    protected abstract void generate(ParticleTextureDataConsumer consumer);

    protected final Identifier[] rangeAscending(Identifier texture, int minInclusive, int maxInclusive) {
        Identifier[] textures = this.createRangeArray(minInclusive, maxInclusive);
        for(int i = minInclusive; i <= maxInclusive; i++) {
            textures[i] = texture.withSuffixedPath("_" + i);
        }
        return textures;
    }

    protected final Identifier[] rangeDescending(Identifier texture, int minInclusive, int maxInclusive) {
        Identifier[] textures = this.createRangeArray(minInclusive, maxInclusive);
        for(int i = minInclusive; i <= maxInclusive; i++) {
            textures[maxInclusive + minInclusive - i] = texture.withSuffixedPath("_" + i);
        }
        return textures;
    }

    private Identifier[] createRangeArray(int minInclusive, int maxInclusive) {
        if(minInclusive < 0 || maxInclusive < 0) {
            throw new IllegalArgumentException("Range cannot be less than 0");
        }else if(minInclusive > maxInclusive) {
            throw new IllegalArgumentException("Min cannot be greater than max");
        }
        return new Identifier[maxInclusive - minInclusive + 1];
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        Map<Identifier, List<Identifier>> map = new Object2ObjectOpenHashMap<>();
        this.generate((particleType, textures) ->
                map.put(Registries.PARTICLE_TYPE.getId(particleType), textures)
        );
        map.forEach((id, textures) -> {
            JsonObject jsonObject = new JsonObject();
            JsonArray jsonArray = new JsonArray();
            textures.forEach(texture -> jsonArray.add(texture.toString()));
            jsonObject.add("textures", jsonArray);
            futures.add(DataProvider.writeToPath(writer, jsonObject, this.pathResolver.resolveJson(id)));
        });
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Particle Texture Data";
    }
}
