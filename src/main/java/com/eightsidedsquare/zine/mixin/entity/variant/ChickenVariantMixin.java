package com.eightsidedsquare.zine.mixin.entity.variant;

import com.eightsidedsquare.zine.common.entity.variant.ZineChickenVariant;
import net.minecraft.entity.passive.ChickenVariant;
import net.minecraft.entity.spawn.SpawnConditionSelectors;
import net.minecraft.util.ModelAndTexture;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ChickenVariant.class)
public abstract class ChickenVariantMixin implements ZineChickenVariant {

    @Shadow @Final @Mutable
    private ModelAndTexture<ChickenVariant.Model> modelAndTexture;

    @Shadow @Final @Mutable
    private SpawnConditionSelectors spawnConditions;

    @Override
    public void zine$setModelAndTexture(ModelAndTexture<ChickenVariant.Model> modelAndTexture) {
        this.modelAndTexture = modelAndTexture;
    }

    @Override
    public void zine$setSpawnConditions(SpawnConditionSelectors spawnConditions) {
        this.spawnConditions = spawnConditions;
    }
}
