package com.eightsidedsquare.zine.common.entity;

import com.eightsidedsquare.zine.common.entity.task.TaskListBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Activity;

import java.util.function.Consumer;

public interface ZineBrain<E extends LivingEntity> {

    default void zine$buildTaskList(Activity activity, Consumer<TaskListBuilder<E>> builderConsumer) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

    default void zine$buildTaskList(Activity activity, int begin, Consumer<TaskListBuilder<E>> builderConsumer) {
        throw new UnsupportedOperationException("Implemented via mixin.");
    }

}
