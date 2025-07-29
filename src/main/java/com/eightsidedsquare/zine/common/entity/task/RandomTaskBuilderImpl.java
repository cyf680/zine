package com.eightsidedsquare.zine.common.entity.task;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.RandomTask;
import net.minecraft.entity.ai.brain.task.Task;

public final class RandomTaskBuilderImpl<E extends LivingEntity> implements RandomTaskBuilder<E> {

    private final ImmutableMap.Builder<MemoryModuleType<?>, MemoryModuleState> requiredMemoryStates = ImmutableMap.builder();
    private final ImmutableList.Builder<Pair<? extends Task<? super E>, Integer>> tasks = ImmutableList.builder();

    @Override
    public RandomTask<E> build() {
        return new RandomTask<>(this.requiredMemoryStates.build(), this.tasks.build());
    }

    @Override
    public RandomTaskBuilder<E> memory(MemoryModuleType<?> memoryModuleType, MemoryModuleState state) {
        this.requiredMemoryStates.put(memoryModuleType, state);
        return this;
    }

    @Override
    public RandomTaskBuilder<E> task(Task<? super E> task) {
        return this.task(task, 1);
    }

    @Override
    public RandomTaskBuilder<E> task(Task<? super E> task, int weight) {
        this.tasks.add(new Pair<>(task, weight));
        return this;
    }
}
