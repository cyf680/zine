package com.eightsidedsquare.zine.mixin.client.font;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.font.BakedGlyph;
import net.minecraft.client.render.VertexConsumer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BakedGlyph.class)
public abstract class BakedGlyphMixin {

    @Shadow protected abstract void draw(boolean italic, float x, float y, float z, Matrix4f matrix, VertexConsumer vertexConsumer, int color, boolean bold, int light);

    @Inject(method = "draw(Lnet/minecraft/client/font/BakedGlyph$DrawnGlyph;Lorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumer;IZ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/BakedGlyph;draw(ZFFFLorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumer;IZI)V", ordinal = 2))
    private void zine$drawOutline(BakedGlyph.DrawnGlyph glyph,
                                  Matrix4f matrix,
                                  VertexConsumer vertexConsumer,
                                  int light,
                                  boolean fixedZ,
                                  CallbackInfo ci,
                                  @Local(ordinal = 1) int color,
                                  @Local(ordinal = 0) float x,
                                  @Local(ordinal = 1) float y,
                                  @Local(ordinal = 2) float baseZOffset,
                                  @Local(ordinal = 3) float z,
                                  @Local(ordinal = 1) boolean italic,
                                  @Local(ordinal = 2) boolean bold) {
        if(glyph.zine$hasOutline()) {
            int outlineColor = glyph.zine$getOutlineColor();
            float zOffset = fixedZ ? 0 : -0.002f;
            this.zine$drawOutline(italic, x, y, z + zOffset, glyph.shadowOffset(), matrix, vertexConsumer, outlineColor, bold, light);
            if(bold) {
                this.zine$drawOutline(italic, x + glyph.boldOffset(), y, z + baseZOffset + zOffset, glyph.shadowOffset(), matrix, vertexConsumer, outlineColor, true, light);
            }
        }
    }

    @Unique
    private void zine$drawOutline(boolean italic, float x, float y, float z, float shadowOffset, Matrix4f matrix, VertexConsumer vertexConsumer, int color, boolean bold, int light) {
        for(int dX = -1; dX <= 1; dX++) {
            for(int dY = -1; dY <= 1; dY++) {
                if(dX == 0 && dY == 0) {
                    continue;
                }
                this.draw(italic, x + dX * shadowOffset, y + dY * shadowOffset, z, matrix, vertexConsumer, color, bold, light);
            }
        }
    }

}
