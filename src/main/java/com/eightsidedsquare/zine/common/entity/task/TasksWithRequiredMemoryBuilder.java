package com.eightsidedsquare.zine.common.entity.task;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;

public interface TasksWithRequiredMemoryBuilder<E extends LivingEntity, T> {

    T task(Task<? super E> task);

    T task(Task<? super E> task, int priority);

    default T task(TaskBuilder<? super E, ?> taskBuilder) {
        return this.task(taskBuilder.build());
    }

    default T task(TaskBuilder<? super E, ?> taskBuilder, int priority) {
        return this.task(taskBuilder.build(), priority);
    }

    T memory(MemoryModuleType<?> memoryModuleType, MemoryModuleState state);

    default T presentMemory(MemoryModuleType<?> memoryModuleType) {
        return this.memory(memoryModuleType, MemoryModuleState.VALUE_PRESENT);
    }

    default T absentMemory(MemoryModuleType<?> memoryModuleType) {
        return this.memory(memoryModuleType, MemoryModuleState.VALUE_ABSENT);
    }

    default T registeredMemory(MemoryModuleType<?> memoryModuleType) {
        return this.memory(memoryModuleType, MemoryModuleState.REGISTERED);
    }

}
