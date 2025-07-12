package com.eightsidedsquare.zine.common.util.codec;

import com.eightsidedsquare.zine.common.util.network.PacketCodecUtil;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.component.ComponentMap;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.Uuids;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class DataHelperImpl<T> implements DataHelper<T> {

    private final List<DataHelper<T>> fields;

    public DataHelperImpl(List<DataHelper<T>> fields) {
        this.fields = fields;
    }

    @Override
    public void read(ReadView view, T object) {
        for (DataHelper<T> field : this.fields) {
            field.read(view, object);
        }
    }

    @Override
    public void write(WriteView view, T object) {
        for (DataHelper<T> field : this.fields) {
            field.write(view, object);
        }
    }

    @Override
    public <I extends RegistryByteBuf> void read(I buf, T object) {
        for (DataHelper<T> field : this.fields) {
            field.read(buf, object);
        }
    }

    @Override
    public <I extends RegistryByteBuf> void write(I buf, T object) {
        for (DataHelper<T> field : this.fields) {
            field.write(buf, object);
        }
    }

    static class BuilderImpl<T> implements Builder<T> {

        private final ImmutableList.Builder<DataHelper<T>> fields = ImmutableList.builder();

        private Builder<T> add(DataHelper<T> field) {
            this.fields.add(field);
            return this;
        }

        @Override
        public <F> FieldBuilder<F, T> field(Codec<F> codec, PacketCodec<? super RegistryByteBuf, F> packetCodec, String key) {
            return (defaultValue, getter, setter) ->
                    this.add(new Field<>(codec, packetCodec, key, defaultValue, getter, setter));
        }

        @Override
        public <F> FieldBuilder<F, T> nullableField(Codec<F> codec, PacketCodec<? super RegistryByteBuf, F> packetCodec, String key) {
            return (defaultValue, getter, setter) -> this.field(
                            Codecs.optional(codec),
                            PacketCodecs.optional(packetCodec.cast()),
                            key
                    )
                    .apply(
                            Optional.ofNullable(defaultValue),
                            t -> Optional.ofNullable(getter.apply(t)),
                            (t, f) -> setter.accept(t, f.orElse(null))
                    );
        }

        @Override
        public <F, L extends Collection<F>> ListFieldBuilder<F, L, T> listField(Codec<L> codec, PacketCodec<? super RegistryByteBuf, L> packetCodec, String key) {
            return getter -> this.add(new ListField<>(codec, packetCodec, key, getter));
        }

        @Override
        public <F> ListFieldBuilder<F, List<F>, T> listFieldOf(Codec<F> codec, PacketCodec<? super RegistryByteBuf, F> packetCodec, String key) {
            return this.listField(codec.listOf(), PacketCodecs.collection(ArrayList::new, packetCodec), key);
        }

        @Override
        public <K, V, M extends Map<K, V>> MapFieldBuilder<K, V, M, T> mapField(Codec<M> codec, PacketCodec<? super RegistryByteBuf, M> packetCodec, String key) {
            return getter -> this.add(new MapField<>(codec, packetCodec, key, getter));
        }

        @Override
        public <K, V> MapFieldBuilder<K, V, Map<K, V>, T> mapField(Codec<K> keyCodec, Codec<V> elementCodec, PacketCodec<? super RegistryByteBuf, K> keyPacketCodec, PacketCodec<? super RegistryByteBuf, V> elementPacketCodec, String key) {
            return this.mapField(Codec.unboundedMap(keyCodec, elementCodec), PacketCodecs.map(HashMap::new, keyPacketCodec, elementPacketCodec), key);
        }

        @Override
        public FieldBuilder<Boolean, T> booleanField(String key) {
            return this.field(Codec.BOOL, PacketCodecs.BOOLEAN, key);
        }

        @Override
        public FieldBuilder<Byte, T> byteField(String key) {
            return this.field(Codec.BYTE, PacketCodecs.BYTE, key);
        }

        @Override
        public FieldBuilder<Short, T> shortField(String key) {
            return this.field(Codec.SHORT, PacketCodecs.SHORT, key);
        }

        @Override
        public FieldBuilder<Integer, T> intField(String key) {
            return this.field(Codec.INT, PacketCodecs.VAR_INT, key);
        }

        @Override
        public FieldBuilder<Long, T> longField(String key) {
            return this.field(Codec.LONG, PacketCodecs.VAR_LONG, key);
        }

        @Override
        public FieldBuilder<Float, T> floatField(String key) {
            return this.field(Codec.FLOAT, PacketCodecs.FLOAT, key);
        }

        @Override
        public FieldBuilder<Double, T> doubleField(String key) {
            return this.field(Codec.DOUBLE, PacketCodecs.DOUBLE, key);
        }

        @Override
        public FieldBuilder<String, T> stringField(String key) {
            return this.field(Codec.STRING, PacketCodecs.STRING, key);
        }

        @Override
        public FieldBuilder<NbtElement, T> nbtElementField(String key) {
            return this.field(Codecs.NBT_ELEMENT, PacketCodecs.NBT_ELEMENT, key);
        }

        @Override
        public FieldBuilder<UUID, T> uuidField(String key) {
            return this.field(Uuids.INT_STREAM_CODEC, Uuids.PACKET_CODEC, key);
        }

        @Override
        public FieldBuilder<BlockPos, T> blockPosField(String key) {
            return this.field(BlockPos.CODEC, BlockPos.PACKET_CODEC, key);
        }

        @Override
        public FieldBuilder<BlockState, T> blockStateField(String key) {
            return this.field(BlockState.CODEC, PacketCodecUtil.BLOCK_STATE, key);
        }

        @Override
        public FieldBuilder<ComponentMap, T> componentMapField(String key) {
            return this.field(ComponentMap.CODEC, PacketCodecUtil.COMPONENT_MAP, key);
        }

        @Override
        public DataHelper<T> build() {
            return new DataHelperImpl<>(this.fields.build());
        }
    }

    static abstract class AbstractField<F, T> implements DataHelper<T> {
        protected final Codec<F> codec;
        protected final PacketCodec<? super RegistryByteBuf, F> packetCodec;
        protected final String key;
        protected final Function<T, F> getter;

        protected AbstractField(Codec<F> codec, PacketCodec<? super RegistryByteBuf, F> packetCodec, String key, Function<T, F> getter) {
            this.codec = codec;
            this.packetCodec = packetCodec;
            this.key = key;
            this.getter = getter;
        }
    }

    static final class Field<F, T> extends AbstractField<F, T> {
        private final F defaultValue;
        private final BiConsumer<T, F> setter;

        Field(Codec<F> codec, PacketCodec<? super RegistryByteBuf, F> packetCodec, String key, F defaultValue, Function<T, F> getter, BiConsumer<T, F> setter) {
            super(codec, packetCodec, key, getter);
            this.defaultValue = defaultValue;
            this.setter = setter;
        }

        @Override
        public void read(ReadView view, T object) {
            this.setter.accept(object, view.read(this.key, this.codec).orElse(this.defaultValue));
        }

        @Override
        public void write(WriteView view, T object) {
            view.put(this.key, this.codec, this.getter.apply(object));
        }

        @Override
        public <I extends RegistryByteBuf> void read(I buf, T object) {
            this.setter.accept(object, this.packetCodec.decode(buf));
        }

        @Override
        public <I extends RegistryByteBuf> void write(I buf, T object) {
            this.packetCodec.encode(buf, this.getter.apply(object));
        }
    }

    static final class ListField<F, L extends Collection<F>, T> extends AbstractField<L, T> {

        public ListField(Codec<L> codec, PacketCodec<? super RegistryByteBuf, L> packetCodec, String key, Function<T, L> getter) {
            super(codec, packetCodec, key, getter);
        }

        @Override
        public void read(ReadView view, T object) {
            L list = this.getter.apply(object);
            list.clear();
            view.read(this.key, this.codec).ifPresent(list::addAll);
        }

        @Override
        public void write(WriteView view, T object) {
            view.put(this.key, this.codec, this.getter.apply(object));
        }

        @Override
        public <I extends RegistryByteBuf> void read(I buf, T object) {
            L list = this.getter.apply(object);
            list.clear();
            list.addAll(this.packetCodec.decode(buf));
        }

        @Override
        public <I extends RegistryByteBuf> void write(I buf, T object) {
            this.packetCodec.encode(buf, this.getter.apply(object));
        }
    }

    static final class MapField<K, V, M extends Map<K, V>, T> extends AbstractField<M, T> {

        public MapField(Codec<M> codec, PacketCodec<? super RegistryByteBuf, M> packetCodec, String key, Function<T, M> getter) {
            super(codec, packetCodec, key, getter);
        }

        @Override
        public void read(ReadView view, T object) {
            M map = this.getter.apply(object);
            map.clear();
            view.read(this.key, this.codec).ifPresent(map::putAll);
        }

        @Override
        public void write(WriteView view, T object) {
            view.put(this.key, this.codec, this.getter.apply(object));
        }

        @Override
        public <I extends RegistryByteBuf> void read(I buf, T object) {
            M map = this.getter.apply(object);
            map.clear();
            map.putAll(this.packetCodec.decode(buf));
        }

        @Override
        public <I extends RegistryByteBuf> void write(I buf, T object) {
            this.packetCodec.encode(buf, this.getter.apply(object));
        }
    }
}
