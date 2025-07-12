package com.eightsidedsquare.zine.mixin.item;

import com.eightsidedsquare.zine.common.item.ZineItemSettings;
import com.eightsidedsquare.zine.core.ZineDataComponentTypes;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.*;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(Item.Settings.class)
public abstract class ItemSettingsMixin implements ZineItemSettings {

    @Shadow public abstract <T> Item.Settings component(ComponentType<T> type, T value);

    @Override
    public Item.Settings zine$nameColor(int color) {
        return this.component(ZineDataComponentTypes.ITEM_NAME_COLOR, color);
    }

    @Override
    public Item.Settings zine$equippable(EquippableComponent component) {
        return this.component(DataComponentTypes.EQUIPPABLE, component);
    }

    @Override
    public Item.Settings zine$container() {
        return this.component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT);
    }

    @Override
    public Item.Settings zine$glintOverride() {
        return this.component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true);
    }

    @Override
    public Item.Settings zine$consumable(ConsumableComponent component) {
        return this.component(DataComponentTypes.CONSUMABLE, component);
    }

    @Override
    public Item.Settings zine$entityBucket() {
        return this.component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
    }

    @Override
    public Item.Settings zine$tool(ToolComponent component) {
        return this.component(DataComponentTypes.TOOL, component);
    }

    @Override
    public Item.Settings zine$potion(float durationScale) {
        if(durationScale != 1) {
            this.component(DataComponentTypes.POTION_DURATION_SCALE, durationScale);
        }
        return this.component(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
    }

    @Override
    public Item.Settings zine$breakSound(RegistryEntry<SoundEvent> soundEvent) {
        return this.component(DataComponentTypes.BREAK_SOUND, soundEvent);
    }

    @Override
    public Item.Settings zine$weapon(int itemDamagePerAttack, float disableBlockingForSeconds) {
        return this.component(DataComponentTypes.WEAPON, new WeaponComponent(itemDamagePerAttack, disableBlockingForSeconds));
    }

    @Override
    public Item.Settings zine$bannerPatterns(TagKey<BannerPattern> bannerPatternTagKey) {
        return this.component(DataComponentTypes.PROVIDES_BANNER_PATTERNS, bannerPatternTagKey);
    }

    @Override
    public Item.Settings zine$glider() {
        return this.component(DataComponentTypes.GLIDER, Unit.INSTANCE);
    }

    @Override
    public Item.Settings zine$tooltipStyle(Identifier texture) {
        return this.component(DataComponentTypes.TOOLTIP_STYLE, texture);
    }

    @Override
    public Item.Settings zine$damageResistant(TagKey<DamageType> damageTypeTagKey) {
        return this.component(DataComponentTypes.DAMAGE_RESISTANT, new DamageResistantComponent(damageTypeTagKey));
    }

    @Override
    public Item.Settings zine$lore(List<Text> lines) {
        return this.component(DataComponentTypes.LORE, new LoreComponent(lines));
    }
}
