package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.item.ItemExtensions;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class ItemMixin implements ItemExtensions {

    @Shadow @Final private ComponentMap components;

    @Override
    public boolean zine$modelEquals(Identifier modelId) {
        return modelId.equals(this.components.get(DataComponentTypes.ITEM_MODEL));
    }
}
