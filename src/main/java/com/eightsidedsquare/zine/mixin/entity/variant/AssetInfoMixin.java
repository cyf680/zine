package com.eightsidedsquare.zine.mixin.entity.variant;

import com.eightsidedsquare.zine.common.entity.variant.ZineAssetInfo;
import net.minecraft.util.AssetInfo;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;

@Mixin(AssetInfo.class)
public abstract class AssetInfoMixin implements ZineAssetInfo {

    @Shadow @Final @Mutable
    private Identifier id;

    @Shadow @Final @Mutable
    private Identifier texturePath;

    @Dynamic("method_67294: unary operator lambda for creating texturePath from id")
    @Shadow
    private static String method_67294(String path) {
        throw new AssertionError();
    }

    @Override
    public void zine$setId(Identifier id, boolean updateTexturePath) {
        this.id = id;
        if(updateTexturePath) {
            this.zine$setTexturePath(this.id.withPath(AssetInfoMixin::method_67294));
        }
    }

    @Override
    public void zine$setTexturePath(Identifier texturePath) {
        this.texturePath = texturePath;
    }
}
