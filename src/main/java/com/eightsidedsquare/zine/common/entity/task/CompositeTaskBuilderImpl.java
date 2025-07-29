package com.eightsidedsquare.zine.common.entity.task;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.CompositeTask;
import net.minecraft.entity.ai.brain.task.Task;

import java.util.Collection;

public final class CompositeTaskBuilderImpl<E extends LivingEntity> implements CompositeTaskBuilder<E> {

    private final ImmutableMap.Builder<MemoryModuleType<?>, MemoryModuleState> requiredMemories = ImmutableMap.builder();
    private final ImmutableSet.Builder<MemoryModuleType<?>> forgettingMemories = ImmutableSet.builder();
    private final ImmutableList.Builder<Pair<? extends Task<? super E>, Integer>> tasks = ImmutableList.builder();
    private CompositeTask.Order order = CompositeTask.Order.ORDERED;
    private CompositeTask.RunMode runMode = CompositeTask.RunMode.RUN_ONE;
    private int priority = 0;

    @Override
    public CompositeTask<E> build() {
        return new CompositeTask<>(this.requiredMemories.build(), this.forgettingMemories.build(), this.order, this.runMode, this.tasks.build());
    }

    @Override
    public CompositeTaskBuilder<E> task(Task<? super E> task) {
        return this.task(task, this.priority);
    }

    @Override
    public CompositeTaskBuilder<E> task(Task<? super E> task, int priority) {
        this.tasks.add(new Pair<>(task, priority));
        this.priority = priority + 1;
        return this;
    }

    @Override
    public CompositeTaskBuilder<E> memory(MemoryModuleType<?> memoryModuleType, MemoryModuleState state) {
        this.requiredMemories.put(memoryModuleType, state);
        return this;
    }

    @Override
    public CompositeTaskBuilder<E> forgetMemory(MemoryModuleType<?> memoryModuleType) {
        this.forgettingMemories.add(memoryModuleType);
        return this;
    }

    @Override
    public CompositeTaskBuilder<E> forgetMemories(Collection<MemoryModuleType<?>> memoryModuleTypes) {
        this.forgettingMemories.addAll(memoryModuleTypes);
        return this;
    }

    @Override
    public CompositeTaskBuilder<E> order(CompositeTask.Order order) {
        this.order = order;
        return this;
    }

    @Override
    public CompositeTaskBuilder<E> runMode(CompositeTask.RunMode runMode) {
        this.runMode = runMode;
        return this;
    }
}
