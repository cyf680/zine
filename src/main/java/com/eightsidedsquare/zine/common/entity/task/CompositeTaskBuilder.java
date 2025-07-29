package com.eightsidedsquare.zine.common.entity.task;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.task.CompositeTask;

public interface CompositeTaskBuilder<E extends LivingEntity> extends TaskBuilder<E, CompositeTask<E>>,
        TasksWithRequiredMemoryBuilder<E, CompositeTaskBuilder<E>>,
        ForgettingMemoryBuilder<CompositeTaskBuilder<E>> {

    static <E extends LivingEntity> CompositeTaskBuilder<E> create() {
        return new CompositeTaskBuilderImpl<>();
    }

    CompositeTaskBuilder<E> order(CompositeTask.Order order);

    default CompositeTaskBuilder<E> ordered() {
        return this.order(CompositeTask.Order.ORDERED);
    }

    default CompositeTaskBuilder<E> shuffled() {
        return this.order(CompositeTask.Order.SHUFFLED);
    }

    CompositeTaskBuilder<E> runMode(CompositeTask.RunMode runMode);

    default CompositeTaskBuilder<E> runOne() {
        return this.runMode(CompositeTask.RunMode.RUN_ONE);
    }

    default CompositeTaskBuilder<E> tryAll() {
        return this.runMode(CompositeTask.RunMode.TRY_ALL);
    }
}
