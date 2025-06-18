package com.eightsidedsquare.zine.mixin.entity.variant;

import com.eightsidedsquare.zine.common.entity.variant.ZinePigVariant;
import net.minecraft.entity.passive.PigVariant;
import net.minecraft.entity.spawn.SpawnConditionSelectors;
import net.minecraft.util.ModelAndTexture;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PigVariant.class)
public abstract class PigVariantMixin implements ZinePigVariant {

    @Shadow @Final @Mutable
    private ModelAndTexture<PigVariant.Model> modelAndTexture;

    @Shadow @Final @Mutable
    private SpawnConditionSelectors spawnConditions;

    @Override
    public void zine$setModelAndTexture(ModelAndTexture<PigVariant.Model> modelAndTexture) {
        this.modelAndTexture = modelAndTexture;
    }

    @Override
    public void zine$setSpawnConditions(SpawnConditionSelectors spawnConditions) {
        this.spawnConditions = spawnConditions;
    }
}
