package com.eightsidedsquare.zine.mixin.client.render;

import com.eightsidedsquare.zine.client.trim.ArmorTrimRegistryImpl;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.equipment.trim.ArmorTrim;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(targets = "net/minecraft/client/render/entity/equipment/EquipmentRenderer$TrimSpriteKey")
public abstract class TrimSpriteKeyMixin {

    @Shadow @Final private ArmorTrim trim;

    @ModifyReturnValue(method = "getTexture", at = @At("RETURN"))
    private Identifier zine$changeTextureNamespace(Identifier original) {
        Optional<RegistryKey<ArmorTrimMaterial>> optional = this.trim.material().getKey();
        if(optional.isPresent() && ArmorTrimRegistryImpl.containsMaterial(optional.get())) {
            return Identifier.of(optional.get().getValue().getNamespace(), original.getPath());
        }
        return original;
    }

}
