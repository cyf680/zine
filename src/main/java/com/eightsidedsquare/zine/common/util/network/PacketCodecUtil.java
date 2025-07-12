package com.eightsidedsquare.zine.common.util.network;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.Component;
import net.minecraft.component.ComponentMap;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.handler.PacketDecoderException;
import net.minecraft.state.State;
import net.minecraft.util.collection.IdList;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public final class PacketCodecUtil {

    public static final PacketCodec<PacketByteBuf, BlockState> BLOCK_STATE = state(Block.STATE_IDS, Block::getRawIdFromState);
    public static final PacketCodec<PacketByteBuf, FluidState> FLUID_STATE = state(Fluid.STATE_IDS, null);
    public static final PacketCodec<RegistryByteBuf, ComponentMap> COMPONENT_MAP = Component.PACKET_CODEC.collect(PacketCodecs.toList()).xmap(
            components -> {
                ComponentMap.Builder builder = ComponentMap.builder();
                components.forEach(builder::zine$add);
                return builder.build();
            },
            componentMap -> ImmutableList.copyOf(componentMap.iterator())
    );

    public static <T extends State<?, ?>> PacketCodec<PacketByteBuf, T> state(IdList<T> idList, @Nullable Function<T, Integer> idGetter) {
        Function<T, Integer> finalIdGetter = idGetter != null ? idGetter : state -> {
            int id = idList.getRawId(state);
            return id == -1 ? 0 : id;
        };
        return PacketCodec.of(
                (state, buf) -> buf.writeVarInt(finalIdGetter.apply(state)),
                buf -> {
                    int id = buf.readVarInt();
                    T state = idList.get(id);
                    if(state == null) {
                        throw new PacketDecoderException("Unknown state with id " + id);
                    }
                    return state;
                }
        );
    }

    private PacketCodecUtil() {
    }
}
