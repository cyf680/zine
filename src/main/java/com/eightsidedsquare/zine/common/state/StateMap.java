package com.eightsidedsquare.zine.common.state;

import net.minecraft.state.State;

public interface StateMap<V> {

    V get(State<?, ?> state);

}

