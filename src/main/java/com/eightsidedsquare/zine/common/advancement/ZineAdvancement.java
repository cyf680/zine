package com.eightsidedsquare.zine.common.advancement;

import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public interface ZineAdvancement {

    default void zine$setParent(@Nullable Identifier parent) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setDisplay(@Nullable AdvancementDisplay display) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setRewards(AdvancementRewards rewards) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$addCriterion(String name, AdvancementCriterion<?> criterion) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default <T extends CriterionConditions> void zine$addCriterion(String name, Criterion<T> trigger, T conditions) {
        this.zine$addCriterion(name, new AdvancementCriterion<>(trigger, conditions));
    }

    default void zine$setCriteria(Map<String, AdvancementCriterion<?>> criteria) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setRequirements(AdvancementRequirements requirements) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setSendsTelemetryEvent(boolean sendsTelemetryEvent) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$setName(@Nullable Text name) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default <T extends CriterionConditions> Optional<T> zine$getCriterion(String name, Criterion<T> criterion) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
