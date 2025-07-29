package com.eightsidedsquare.zine.common.entity.task;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.task.Task;

@FunctionalInterface
public interface TaskBuilder<E extends LivingEntity, T extends Task<? super E>> {

    T build();

}
