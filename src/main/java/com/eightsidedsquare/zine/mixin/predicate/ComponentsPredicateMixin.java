package com.eightsidedsquare.zine.mixin.predicate;

import com.eightsidedsquare.zine.common.predicate.ZineComponentsPredicate;
import com.eightsidedsquare.zine.common.util.ZineUtil;
import net.minecraft.predicate.component.ComponentMapPredicate;
import net.minecraft.predicate.component.ComponentPredicate;
import net.minecraft.predicate.component.ComponentsPredicate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(ComponentsPredicate.class)
public abstract class ComponentsPredicateMixin implements ZineComponentsPredicate {

    @Shadow @Final @Mutable
    private ComponentMapPredicate exact;

    @Shadow @Final @Mutable
    private Map<ComponentPredicate.Type<?>, ComponentPredicate> partial;

    @Override
    public void zine$setExact(ComponentMapPredicate exact) {
        this.exact = exact;
    }

    @Override
    public void zine$setPartial(Map<ComponentPredicate.Type<?>, ComponentPredicate> partial) {
        this.partial = partial;
    }

    @Override
    public void zine$addPartial(ComponentPredicate.Type<?> type, ComponentPredicate predicate) {
        this.partial = ZineUtil.putOrUnfreeze(this.partial, type, predicate);
    }
}
