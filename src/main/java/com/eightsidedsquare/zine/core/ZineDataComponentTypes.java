package com.eightsidedsquare.zine.core;

import com.eightsidedsquare.zine.common.util.codec.CodecUtil;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;

public interface ZineDataComponentTypes {

    ComponentType<Integer> ITEM_NAME_COLOR = ZineMod.REGISTRY.dataComponent("item_name_color", builder ->
            builder.codec(CodecUtil.COLOR).packetCodec(PacketCodecs.VAR_INT)
    );

    static void init() {

    }

}
