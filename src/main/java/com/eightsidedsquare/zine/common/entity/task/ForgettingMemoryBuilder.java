package com.eightsidedsquare.zine.common.entity.task;

import net.minecraft.entity.ai.brain.MemoryModuleType;

import java.util.Collection;

public interface ForgettingMemoryBuilder<T> {

    T forgetMemory(MemoryModuleType<?> memoryModuleType);

    T forgetMemories(Collection<MemoryModuleType<?>> memoryModuleTypes);

}
