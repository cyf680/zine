package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.advancement.AdvancementDisplayExtensions;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(AdvancementDisplay.class)
public abstract class AdvancementDisplayMixin implements AdvancementDisplayExtensions {

    @Shadow @Final @Mutable
    private Text title;

    @Shadow @Final @Mutable
    private Text description;

    @Shadow @Final @Mutable
    private ItemStack icon;

    @Shadow @Final @Mutable
    private Optional<Identifier> background;

    @Shadow @Final @Mutable
    private AdvancementFrame frame;

    @Shadow @Final @Mutable
    private boolean showToast;

    @Shadow @Final @Mutable
    private boolean announceToChat;

    @Shadow @Final @Mutable
    private boolean hidden;

    @Override
    public void zine$setTitle(Text title) {
        this.title = title;
    }

    @Override
    public void zine$setDescription(Text description) {
        this.description = description;
    }

    @Override
    public void zine$setIcon(ItemStack icon) {
        this.icon = icon;
    }

    @Override
    public void zine$setBackground(@Nullable Identifier background) {
        this.background = Optional.ofNullable(background);
    }

    @Override
    public void zine$setFrame(AdvancementFrame frame) {
        this.frame = frame;
    }

    @Override
    public void zine$setShowToast(boolean showToast) {
        this.showToast = showToast;
    }

    @Override
    public void zine$setAnnounceToChat(boolean announceToChat) {
        this.announceToChat = announceToChat;
    }

    @Override
    public void zine$setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
