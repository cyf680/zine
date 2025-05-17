package com.eightsidedsquare.zine.client.state;

/**
 * Allows storage of arbitrary data for {@link net.minecraft.client.render.entity.state.EntityRenderState}
 * @param <T> Type of render state data
 * @see EntityRenderStateExtensions
 */
public record RenderStateDataKey<T>() {
}
