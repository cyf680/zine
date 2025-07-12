package com.eightsidedsquare.zine.mixin.structure;

import com.eightsidedsquare.zine.common.util.ZineUtil;
import com.eightsidedsquare.zine.common.world.structure.ZineStructurePool;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(StructurePool.class)
public abstract class StructurePoolMixin implements ZineStructurePool {

    @Shadow @Final
    private ObjectArrayList<StructurePoolElement> elements;

    @Mutable @Shadow @Final
    private List<Pair<StructurePoolElement, Integer>> elementWeights;

    @Shadow
    private int highestY;

    @Shadow @Final @Mutable
    private RegistryEntry<StructurePool> fallback;

    @Override
    public ObjectArrayList<StructurePoolElement> zine$getElements() {
        return this.elements;
    }

    @Override
    public List<Pair<StructurePoolElement, Integer>> zine$getElementWeights() {
        return this.elementWeights;
    }

    @Override
    public void zine$addElement(StructurePoolElement element, int weight) {
        if(weight < 1 || weight > 150) {
            return;
        }
        for(int i = 0; i < weight; i++) {
            this.elements.add(element);
        }
        this.elementWeights = ZineUtil.addOrUnfreeze(this.elementWeights, new Pair<>(element, weight));
        // Reset highest y so it can be recalculated
        this.highestY = Integer.MIN_VALUE;
    }

    @Override
    public void zine$setFallback(RegistryEntry<StructurePool> fallback) {
        this.fallback = fallback;
    }
}
