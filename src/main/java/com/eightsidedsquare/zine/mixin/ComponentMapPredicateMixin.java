package com.eightsidedsquare.zine.mixin;

import com.eightsidedsquare.zine.common.predicate.ComponentMapPredicateExtensions;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.component.Component;
import net.minecraft.predicate.component.ComponentMapPredicate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(ComponentMapPredicate.class)
public abstract class ComponentMapPredicateMixin implements ComponentMapPredicateExtensions {

    @Shadow @Final @Mutable
    private List<Component<?>> components;

    @Override
    public void zine$setComponents(List<Component<?>> components) {
        this.components = components;
    }

    @Override
    public void zine$addComponent(Component<?> component) {
        this.components = ZineUtil.addOrUnfreeze(this.components, component);
    }

    @Override
    public void zine$addComponents(List<Component<?>> components) {
        this.components = ZineUtil.addAllOrUnfreeze(this.components, components);
    }
}
