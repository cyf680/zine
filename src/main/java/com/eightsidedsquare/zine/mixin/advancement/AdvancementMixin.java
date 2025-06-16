package com.eightsidedsquare.zine.mixin.advancement;

import com.eightsidedsquare.zine.common.advancement.ZineAdvancement;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;
import java.util.Optional;

@Mixin(Advancement.class)
public abstract class AdvancementMixin implements ZineAdvancement {

    @Shadow @Final @Mutable
    private Optional<Identifier> parent;

    @Shadow @Final @Mutable
    private Optional<AdvancementDisplay> display;

    @Shadow @Final @Mutable
    private AdvancementRewards rewards;

    @Shadow @Final @Mutable
    private Map<String, AdvancementCriterion<?>> criteria;

    @Shadow @Final @Mutable
    private boolean sendsTelemetryEvent;

    @Shadow @Final @Mutable
    private Optional<Text> name;

    @Shadow @Final @Mutable
    private AdvancementRequirements requirements;

    @Override
    public void zine$setParent(@Nullable Identifier parent) {
        this.parent = Optional.ofNullable(parent);
    }

    @Override
    public void zine$setDisplay(@Nullable AdvancementDisplay display) {
        this.display = Optional.ofNullable(display);
    }

    @Override
    public void zine$setRewards(AdvancementRewards rewards) {
        this.rewards = rewards;
    }

    @Override
    public void zine$addCriterion(String name, AdvancementCriterion<?> criterion) {
        this.criteria = ZineUtil.putOrUnfreeze(this.criteria, name, criterion);
    }

    @Override
    public void zine$setCriteria(Map<String, AdvancementCriterion<?>> criteria) {
        this.criteria = criteria;
    }

    @Override
    public void zine$setRequirements(AdvancementRequirements requirements) {
        this.requirements = requirements;
    }

    @Override
    public void zine$setSendsTelemetryEvent(boolean sendsTelemetryEvent) {
        this.sendsTelemetryEvent = sendsTelemetryEvent;
    }

    @Override
    public void zine$setName(@Nullable Text name) {
        this.name = Optional.ofNullable(name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends CriterionConditions> Optional<T> zine$getCriterion(String name, Criterion<T> criterion) {
        AdvancementCriterion<?> advancementCriterion = this.criteria.get(name);
        if(advancementCriterion != null && advancementCriterion.trigger().equals(criterion)) {
            return Optional.of((T) advancementCriterion.conditions());
        }
        return Optional.empty();
    }
}
