package com.eightsidedsquare.zine.common.util;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.util.Util;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.EulerAngle;
import org.joml.Vector2f;

import java.util.List;
import java.util.function.Function;

public final class CodecUtil {

    public static final Codec<NbtElement> NBT_ELEMENT = Codecs.fromOps(NbtOps.INSTANCE);
    public static final Codec<Vector2f> VECTOR_2F = Codec.FLOAT.listOf().comapFlatMap(
            list -> Util.decodeFixedLengthList(list, 2).map(floats -> new Vector2f(floats.getFirst(), floats.get(1))),
            vec2f -> List.of(vec2f.x(), vec2f.y())
    );
    public static final Codec<EulerAngle> EULER_ANGLE = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.optionalFieldOf("pitch", 0f).forGetter(EulerAngle::getPitch),
            Codec.FLOAT.optionalFieldOf("yaw", 0f).forGetter(EulerAngle::getYaw),
            Codec.FLOAT.optionalFieldOf("roll", 0f).forGetter(EulerAngle::getRoll)
    ).apply(instance, EulerAngle::new));
    public static final Codec<Integer> INT_STRING = Codec.STRING.comapFlatMap(
            string -> {
                int value;
                try {
                    value = Integer.parseInt(string);
                } catch (NumberFormatException e) {
                    return DataResult.error(() -> "Failed to parse int from " + string);
                }
                return DataResult.success(value);
            },
            String::valueOf
    );

    private CodecUtil() {
    }

    /**
     * Creates a list codec that can deserialize single elements as a list,
     * and serialize lists of size 1 as a single element.
     * @param codec the codec of a single element
     * @param <A> the type of the list's element
     */
    public static <A> Codec<List<A>> listCodec(Codec<A> codec) {
        return Codec.either(codec, codec.listOf()).xmap(
                either -> either.map(List::of, list -> list),
                list -> list.size() == 1 ? Either.left(list.getFirst()) : Either.right(list)
        );
    }

    /**
     * Creates a list codec using {@link #listCodec(Codec)} with validation to prevent empty lists.
     * @param codec the codec of a single element
     * @param <A> the type of the list's element
     */
    public static <A> Codec<List<A>> nonEmptyListCodec(Codec<A> codec) {
        return listCodec(codec).validate(list -> list.isEmpty() ? DataResult.error(() -> "Empty list") : DataResult.success(list));
    }

    public static <O, T> Codec<O> codec(RecordCodecBuilder<O, T> field, Function<T, O> function) {
        return RecordCodecBuilder.create(instance -> instance.group(field).apply(instance, function));
    }

    public static <O, T> MapCodec<O> mapCodec(RecordCodecBuilder<O, T> field, Function<T, O> function) {
        return RecordCodecBuilder.mapCodec(instance -> instance.group(field).apply(instance, function));
    }
}
