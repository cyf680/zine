package com.eightsidedsquare.zine.mixin.client;

import com.eightsidedsquare.zine.client.state.ZineEntityRenderState;
import com.eightsidedsquare.zine.client.state.RenderStateDataKey;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import net.minecraft.client.render.entity.state.EntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityRenderState.class)
public abstract class EntityRenderStateMixin implements ZineEntityRenderState {

    @Unique
    private final Reference2ObjectMap<RenderStateDataKey<?>, Object> data = new Reference2ObjectArrayMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T> T zine$getData(RenderStateDataKey<T> key) {
        return (T) this.data.get(key);
    }

    @Override
    public <T> void zine$setData(RenderStateDataKey<T> key, T value) {
        this.data.put(key, value);
    }

    @Override
    public void zine$clearData() {
        this.data.clear();
    }
}
