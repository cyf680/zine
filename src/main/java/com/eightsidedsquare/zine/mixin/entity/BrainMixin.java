package com.eightsidedsquare.zine.mixin.entity;

import com.eightsidedsquare.zine.common.entity.ZineBrain;
import com.eightsidedsquare.zine.common.entity.task.TaskListBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Consumer;

@Mixin(Brain.class)
public abstract class BrainMixin implements ZineBrain<LivingEntity> {

    @SuppressWarnings("unchecked")
    @Override
    public void zine$buildTaskList(Activity activity, Consumer<TaskListBuilder<LivingEntity>> builderConsumer) {
        TaskListBuilder<LivingEntity> taskListBuilder = TaskListBuilder.create((Brain<LivingEntity>) (Object) this, activity);
        builderConsumer.accept(taskListBuilder);
        taskListBuilder.build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void zine$buildTaskList(Activity activity, int begin, Consumer<TaskListBuilder<LivingEntity>> builderConsumer) {
        TaskListBuilder<LivingEntity> taskListBuilder = TaskListBuilder.create((Brain<LivingEntity>) (Object) this, activity, begin);
        builderConsumer.accept(taskListBuilder);
        taskListBuilder.build();
    }
}
