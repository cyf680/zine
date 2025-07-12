package com.eightsidedsquare.zine.common.item;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public interface ZineItemSettings {

    default Item.Settings zine$nameColor(int color) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$equippable(EquippableComponent component) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$equippable(EquippableComponent.Builder builder) {
        return this.zine$equippable(builder.build());
    }

    default Item.Settings zine$container() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$glintOverride() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$consumable(ConsumableComponent component) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$consumable(ConsumableComponent.Builder builder) {
        return this.zine$consumable(builder.build());
    }

    default Item.Settings zine$entityBucket() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$tool(ToolComponent component) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$potion(float durationScale) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$potion() {
        return this.zine$potion(1);
    }

    default Item.Settings zine$breakSound(RegistryEntry<SoundEvent> soundEvent) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$weapon(int itemDamagePerAttack, float disableBlockingForSeconds) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$weapon(int itemDamagePerAttack) {
        return this.zine$weapon(itemDamagePerAttack, 0);
    }

    default Item.Settings zine$bannerPatterns(TagKey<BannerPattern> bannerPatternTagKey) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$glider() {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$tooltipStyle(Identifier texture) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$damageResistant(TagKey<DamageType> damageTypeTagKey) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$lore(List<Text> lines) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default Item.Settings zine$lore(Text... lines) {
        return this.zine$lore(List.of(lines));
    }

}
