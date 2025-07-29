package com.eightsidedsquare.zinetest.mixin;

import com.eightsidedsquare.zine.common.entity.task.CompositeTaskBuilder;
import com.eightsidedsquare.zine.common.entity.task.RandomTaskBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.passive.AxolotlBrain;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(AxolotlBrain.class)
public abstract class AxolotlBrainMixin {

    @Shadow @Final private static UniformIntProvider WALK_TOWARD_ADULT_RANGE;

    @Shadow
    private static float getTemptedSpeed(LivingEntity entity) {
        throw new AssertionError();
    }

    @Shadow
    private static float getAdultFollowingSpeed(LivingEntity entity) {
        throw new AssertionError();
    }

    @Shadow
    private static Optional<? extends LivingEntity> getAttackTarget(ServerWorld world, AxolotlEntity axolotl) {
        throw new AssertionError();
    }

    @Shadow
    private static boolean canGoToLookTarget(LivingEntity entity) {
        throw new AssertionError();
    }

    @SuppressWarnings("deprecation")
    @Inject(method = "addIdleActivities", at = @At("HEAD"), cancellable = true)
    private static void zinetest$addIdleActivities(Brain<AxolotlEntity> brain, CallbackInfo ci) {
        brain.zine$buildTaskList(Activity.IDLE, builder -> builder
                .task(LookAtMobWithIntervalTask.follow(EntityType.PLAYER, 6.0F, UniformIntProvider.create(30, 60)))
                .task(new BreedTask(EntityType.AXOLOTL, 0.2F, 2))
                .task(
                        RandomTaskBuilder.<AxolotlEntity>create()
                                .task(new TemptTask(AxolotlBrainMixin::getTemptedSpeed))
                                .task(WalkTowardsEntityTask.create(WALK_TOWARD_ADULT_RANGE, AxolotlBrainMixin::getAdultFollowingSpeed, MemoryModuleType.NEAREST_VISIBLE_ADULT, false))
                )
                .task(UpdateAttackTargetTask.create(AxolotlBrainMixin::getAttackTarget), 3)
                .task(SeekWaterTask.create(6, 0.15F), 3)
                .task(
                        CompositeTaskBuilder.<AxolotlEntity>create()
                                .absentMemory(MemoryModuleType.WALK_TARGET)
                                .ordered()
                                .tryAll()
                                .task(StrollTask.createDynamicRadius(0.5F), 2)
                                .task(StrollTask.create(0.15F, false), 2)
                                .task(GoToLookTargetTask.create(AxolotlBrainMixin::canGoToLookTarget, AxolotlBrainMixin::getTemptedSpeed, 3), 3)
                                .task(TaskTriggerer.predicate(Entity::isTouchingWater), 5)
                                .task(TaskTriggerer.predicate(Entity::isOnGround), 5)
                )
        );
        ci.cancel();
    }

}
