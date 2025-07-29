package com.eightsidedsquare.zine.common.entity.task;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;

public interface TaskListBuilder<E extends LivingEntity> extends TasksWithRequiredMemoryBuilder<E, TaskListBuilder<E>>, ForgettingMemoryBuilder<TaskListBuilder<E>> {

    static <E extends LivingEntity> TaskListBuilder<E> create(Brain<E> brain, Activity activity, int begin) {
        return new TaskListBuilderImpl<>(brain, activity, begin);
    }

    static <E extends LivingEntity> TaskListBuilder<E> create(Brain<E> brain, Activity activity) {
        return new TaskListBuilderImpl<>(brain, activity);
    }

    void build();

}
