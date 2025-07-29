package com.eightsidedsquare.zine.common.entity.task;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.task.RandomTask;

public interface RandomTaskBuilder<E extends LivingEntity> extends TaskBuilder<E, RandomTask<E>>, TasksWithRequiredMemoryBuilder<E, RandomTaskBuilder<E>> {

    static <E extends LivingEntity> RandomTaskBuilder<E> create() {
        return new RandomTaskBuilderImpl<>();
    }

}
