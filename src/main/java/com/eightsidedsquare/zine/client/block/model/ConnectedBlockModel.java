package com.eightsidedsquare.zine.client.block.model;

import com.eightsidedsquare.zine.client.util.ConnectedPattern;
import com.eightsidedsquare.zine.client.util.ConnectedShape;
import com.eightsidedsquare.zine.common.util.Offset;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.*;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

//public class ConnectedBlockModel implements UnbakedModel, BakedModel, FabricBakedModel {
//
//    protected static final Identifier PARENT_ID = Identifier.ofVanilla("block/block");
//    protected static final Offset[] X_OFFSETS = {
//            Offset.UP_SOUTH, Offset.UP, Offset.UP_NORTH, Offset.NORTH, Offset.DOWN_NORTH, Offset.DOWN, Offset.DOWN_SOUTH, Offset.SOUTH
//    };
//    protected static final Offset[] Y_OFFSETS = {
//            Offset.NORTHWEST, Offset.NORTH, Offset.NORTHEAST, Offset.EAST, Offset.SOUTHEAST, Offset.SOUTH, Offset.SOUTHWEST, Offset.WEST
//    };
//    protected static final Offset[] Z_OFFSETS = {
//            Offset.UP_WEST, Offset.UP, Offset.UP_EAST, Offset.EAST, Offset.DOWN_EAST, Offset.DOWN, Offset.DOWN_WEST, Offset.WEST
//    };
//    protected final EnumMap<ConnectedPattern, SpriteIdentifier> spriteIds = new EnumMap<>(ConnectedPattern.class);
//    protected final EnumMap<ConnectedPattern, Mesh[]> meshes = new EnumMap<>(ConnectedPattern.class);
//    protected JsonUnbakedModel parent;
//    protected Sprite particleSprite;
//
//    public ConnectedBlockModel(Identifier baseName,
//                               Identifier allTexture,
//                               Identifier cornersTexture,
//                               Identifier horizontalTexture,
//                               Identifier noneTexture,
//                               Identifier verticalTexture) {
//        for(ConnectedPattern pattern : ConnectedPattern.values()) {
//            if(pattern.allMatch()) {
//                this.spriteIds.put(pattern, spriteId(switch (pattern.getNW()) {
//                    case ALL -> allTexture;
//                    case CORNER -> cornersTexture;
//                    case HORIZONTAL -> horizontalTexture;
//                    case NONE -> noneTexture;
//                    case VERTICAL -> verticalTexture;
//                }));
//            }else {
//                this.spriteIds.put(pattern, spriteId(pattern.addSuffix(baseName)));
//            }
//        }
//    }
//
//    public ConnectedBlockModel(Identifier baseName) {
//        this(
//                baseName,
//                baseName.withSuffixedPath("_all"),
//                baseName.withSuffixedPath("_corners"),
//                baseName.withSuffixedPath("_horizontal"),
//                baseName.withSuffixedPath("_none"),
//                baseName.withSuffixedPath("_vertical")
//        );
//    }
//
//    protected int getFlags(Predicate<BlockState> predicate, BlockView world, BlockPos pos) {
//        BlockPos.Mutable mutable = pos.mutableCopy();
//        int flags = 0;
//        for(Offset offset : Offset.values(this.getOffsetDistance())) {
//            if(predicate.test(world.getBlockState(mutable.set(pos, offset.getX(), offset.getY(), offset.getZ())))) {
//                flags |= 1 << offset.ordinal();
//            }
//        }
//        return flags;
//    }
//
//    protected int getOffsetDistance() {
//        return 2;
//    }
//
//    protected boolean getFlag(int flags, Offset offset) {
//        return (flags & (1 << offset.ordinal())) != 0;
//    }
//
//    protected ConnectedShape getShape(Offset[] offsets, int flags, int vertical, int horizontal, int corner) {
//        if(this.getFlag(flags, offsets[vertical])) {
//            if(this.getFlag(flags, offsets[horizontal])) {
//                if(this.getFlag(flags, offsets[corner])) {
//                    return ConnectedShape.NONE;
//                }
//                return ConnectedShape.CORNER;
//            }
//            return ConnectedShape.VERTICAL;
//        }else if(this.getFlag(flags, offsets[horizontal])) {
//            return ConnectedShape.HORIZONTAL;
//        }
//        return ConnectedShape.ALL;
//    }
//
//    protected ConnectedPattern getPattern(Direction.Axis axis, int flags) {
//        Offset[] offsets = switch (axis) {
//            case X -> X_OFFSETS;
//            case Y -> Y_OFFSETS;
//            case Z -> Z_OFFSETS;
//        };
//        return ConnectedPattern.from(
//                this.getShape(offsets, flags, 1, 7, 0),
//                this.getShape(offsets, flags, 1, 3, 2),
//                this.getShape(offsets, flags, 5, 3, 4),
//                this.getShape(offsets, flags, 5, 7, 6)
//        );
//    }
//
//    @SuppressWarnings("deprecation")
//    private static SpriteIdentifier spriteId(Identifier id) {
//        return new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, id);
//    }
//
//    @Override
//    public void emitBlockQuads(QuadEmitter emitter, BlockRenderView world, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, Predicate<@Nullable Direction> cullTest) {
//        Block block = state.getBlock();
//        int flags = this.getFlags(blockState -> blockState.isOf(block), world, pos);
//        for(Direction.Axis axis : Direction.Axis.values()) {
//            this.meshes.get(this.getPattern(axis, flags))[axis.ordinal()].outputTo(emitter);
//        }
//    }
//
//    @Override
//    public void emitItemQuads(QuadEmitter emitter, Supplier<Random> randomSupplier) {
//        Mesh[] meshes = this.meshes.get(ConnectedPattern.AAAA);
//        for(Mesh mesh : meshes) {
//            mesh.outputTo(emitter);
//        }
//    }
//
//    @Override
//    public BakedModel bake(ModelTextures textures, Baker baker, ModelBakeSettings settings, boolean ambientOcclusion, boolean isSideLit, ModelTransformation transformation) {
//        SpriteGetter spriteGetter = baker.getSpriteGetter();
//
//        Renderer renderer = Renderer.get();
//        if (renderer == null) {
//            return this;
//        }
//        MutableMesh builder = renderer.mutableMesh();
//        QuadEmitter emitter = builder.emitter();
//
//        EnumMap<ConnectedPattern, Sprite> sprites = new EnumMap<>(ConnectedPattern.class);
//
//        for(Map.Entry<ConnectedPattern, SpriteIdentifier> entry : this.spriteIds.entrySet()) {
//            sprites.put(entry.getKey(), spriteGetter.get(entry.getValue()));
//        }
//
//        for(ConnectedPattern pattern : ConnectedPattern.values()) {
//            Sprite frontSprite = sprites.get(pattern);
//            Sprite backSideSprite = sprites.get(pattern.flipHorizontal());
//            Sprite backBottomSprite = sprites.get(pattern.flipVertical());
//            this.emitMeshes(builder, emitter, pattern, frontSprite, backBottomSprite, backSideSprite);
//        }
//        builder.clear();
//        this.particleSprite = sprites.get(ConnectedPattern.AAAA);
//        return this;
//    }
//
//    protected void emitMeshes(MutableMesh builder, QuadEmitter emitter, ConnectedPattern pattern, Sprite frontSprite, Sprite backBottomSprite, Sprite backSideSprite) {
//        for(Direction.Axis axis : Direction.Axis.values()) {
//            builder.clear();
//            this.emitMeshForAxis(axis, frontSprite, axis == Direction.Axis.Y ? backBottomSprite : backSideSprite, emitter);
//            this.meshes.computeIfAbsent(pattern, p -> new Mesh[3])[axis.ordinal()] = builder.immutableCopy();
//        }
//    }
//
//    protected void emitMeshForAxis(Direction.Axis axis, Sprite frontSprite, Sprite backSprite, QuadEmitter emitter) {
//        this.emitMeshForDirection(axis.getPositiveDirection(), frontSprite, emitter);
//        this.emitMeshForDirection(axis.getNegativeDirection(), backSprite, emitter);
//    }
//
//    protected void emitMeshForDirection(Direction direction, Sprite sprite, QuadEmitter emitter) {
//        emitter.square(direction, 0, 0, 1, 1, 0);
//        emitter.spriteBake(sprite, MutableQuadView.BAKE_LOCK_UV);
//        emitter.color(-1, -1, -1, -1);
//        emitter.emit();
//    }
//
//    @Override
//    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, Random random) {
//        return List.of();
//    }
//
//    @Override
//    public boolean useAmbientOcclusion() {
//        return true;
//    }
//
//    @Override
//    public boolean hasDepth() {
//        return false;
//    }
//
//    @Override
//    public boolean isSideLit() {
//        return false;
//    }
//
//    @Override
//    public Sprite getParticleSprite() {
//        return this.particleSprite;
//    }
//
//    @Override
//    public @Nullable Boolean getAmbientOcclusion() {
//        return true;
//    }
//
//    @Override
//    public @Nullable GuiLight getGuiLight() {
//        return GuiLight.BLOCK;
//    }
//
//    @Override
//    public @Nullable ModelTransformation getTransformation() {
//        return this.parent == null ? ModelTransformation.NONE : this.parent.getTransformation();
//    }
//
//    @Override
//    public void resolve(Resolver resolver) {
//        if(resolver.resolve(PARENT_ID) instanceof JsonUnbakedModel model) {
//            this.parent = model;
//        }
//    }
//
//    @Override
//    public boolean isVanillaAdapter() {
//        return false;
//    }
//}
