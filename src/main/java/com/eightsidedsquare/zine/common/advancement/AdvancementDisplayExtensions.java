package com.eightsidedsquare.zine.common.advancement;

import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public interface AdvancementDisplayExtensions {

    default void zine$setTitle(Text title) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setDescription(Text description) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setIcon(ItemStack icon) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setBackground(@Nullable Identifier background) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setFrame(AdvancementFrame frame) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setShowToast(boolean showToast) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setAnnounceToChat(boolean announceToChat) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setHidden(boolean hidden) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
