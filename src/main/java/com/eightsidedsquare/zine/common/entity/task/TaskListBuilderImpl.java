package com.eightsidedsquare.zine.common.entity.task;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;

import java.util.Collection;
import java.util.Set;

public final class TaskListBuilderImpl<E extends LivingEntity> implements TaskListBuilder<E> {

    private final Brain<E> brain;
    private final Activity activity;
    private final ImmutableList.Builder<Pair<Integer, ? extends Task<? super E>>> indexedTasks = ImmutableList.builder();
    private final ImmutableSet.Builder<Pair<MemoryModuleType<?>, MemoryModuleState>> requiredMemories = ImmutableSet.builder();
    private final Set<MemoryModuleType<?>> forgettingMemories = Sets.newHashSet();
    private int priority;

    public TaskListBuilderImpl(Brain<E> brain, Activity activity, int priority) {
        this.brain = brain;
        this.activity = activity;
        this.priority = priority;
    }

    public TaskListBuilderImpl(Brain<E> brain, Activity activity) {
        this(brain, activity, 0);
    }

    @Override
    public void build() {
        this.brain.setTaskList(this.activity, this.indexedTasks.build(), this.requiredMemories.build(), this.forgettingMemories);
    }

    @Override
    public TaskListBuilder<E> task(Task<? super E> task) {
        return this.task(task, this.priority);
    }

    @Override
    public TaskListBuilder<E> task(Task<? super E> task, int priority) {
        this.indexedTasks.add(new Pair<>(priority, task));
        this.priority = priority + 1;
        return this;
    }

    @Override
    public TaskListBuilder<E> memory(MemoryModuleType<?> memoryModuleType, MemoryModuleState state) {
        this.requiredMemories.add(new Pair<>(memoryModuleType, state));
        return this;
    }

    @Override
    public TaskListBuilder<E> forgetMemory(MemoryModuleType<?> memoryModuleType) {
        this.forgettingMemories.add(memoryModuleType);
        return this;
    }

    @Override
    public TaskListBuilder<E> forgetMemories(Collection<MemoryModuleType<?>> memoryModuleTypes) {
        this.forgettingMemories.addAll(memoryModuleTypes);
        return this;
    }
}
