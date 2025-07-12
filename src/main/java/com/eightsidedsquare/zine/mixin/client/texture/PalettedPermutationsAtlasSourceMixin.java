package com.eightsidedsquare.zine.mixin.client.texture;

import com.eightsidedsquare.zine.client.atlas.ZinePalettedPermutationsAtlasSource;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.client.texture.atlas.PalettedPermutationsAtlasSource;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;

@Mixin(PalettedPermutationsAtlasSource.class)
public abstract class PalettedPermutationsAtlasSourceMixin implements ZinePalettedPermutationsAtlasSource {

    @Shadow @Final @Mutable
    private List<Identifier> textures;
    @Shadow @Final @Mutable
    private Map<String, Identifier> permutations;
    @Shadow @Final @Mutable
    private Identifier paletteKey;
    @Unique
    private final Set<String> namespacedPermutations = new HashSet<>();

    @Override
    public void zine$addPermutation(String name, Identifier texture) {
        try {
            this.permutations.put(name, texture);
        } catch (UnsupportedOperationException e) {
            this.permutations = new Object2ObjectOpenHashMap<>(this.permutations);
            this.permutations.put(name, texture);
        }
    }

    @Override
    public void zine$addNamespacedPermutation(String name, Identifier texture) {
        this.namespacedPermutations.add(name);
        this.zine$addPermutation(name, texture);
    }

    @Override
    public void zine$addTexture(Identifier texture) {
        try {
            this.textures.add(texture);
        } catch (UnsupportedOperationException e) {
            this.textures = new ArrayList<>(this.textures);
            this.textures.add(texture);
        }
    }

    @Override
    public Identifier zine$getPaletteKey() {
        return this.paletteKey;
    }

    @Override
    public void zine$setPaletteKey(Identifier paletteKey) {
        this.paletteKey = paletteKey;
    }

    @ModifyExpressionValue(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Identifier;withSuffixedPath(Ljava/lang/String;)Lnet/minecraft/util/Identifier;"))
    private Identifier zine$swapPermutationNamespace(Identifier original, @Local Map.Entry<String, Supplier<IntUnaryOperator>> entry) {
        String permutation = entry.getKey();
        if(this.namespacedPermutations.contains(permutation)) {
            return Identifier.of(this.permutations.get(permutation).getNamespace(), original.getPath());
        }
        return original;
    }
}
