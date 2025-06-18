package com.eightsidedsquare.zine.common.util;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class ZineUtil {
    private ZineUtil() {
    }

    public static final EquipmentType[] HUMANOID_EQUIPMENT_TYPES = new EquipmentType[]{
            EquipmentType.HELMET,
            EquipmentType.CHESTPLATE,
            EquipmentType.LEGGINGS,
            EquipmentType.BOOTS
    };

    public static <T> List<T> addOrUnfreeze(List<T> list, T value) {
        try {
            list.add(value);
        } catch (UnsupportedOperationException e) {
            list = new ObjectArrayList<>(list);
            list.add(value);
        }
        return list;
    }

    public static <T> List<T> addAllOrUnfreeze(List<T> list, Collection<T> values) {
        try {
            list.addAll(values);
        } catch (UnsupportedOperationException e) {
            list = new ObjectArrayList<>(list);
            list.addAll(values);
        }
        return list;
    }

    public static <T> List<T> setOrUnfreeze(List<T> list, int index, T value) {
        try {
            list.set(index, value);
        } catch (UnsupportedOperationException e) {
            list = new ObjectArrayList<>(list);
            list.set(index, value);
        }
        return list;
    }

    public static <K, V> Map<K, V> putOrUnfreeze(Map<K, V> map, K key, V value) {
        try {
            map.put(key, value);
        } catch (UnsupportedOperationException e) {
            map = new Object2ObjectOpenHashMap<>(map);
            map.put(key, value);
        }
        return map;
    }

    public static <E, T> RegistryEntryList<T> mergeValue(RegistryEntryList<T> registryEntryList, Function<E, RegistryEntry<T>> mapper, E value) {
        if(registryEntryList.size() == 0) {
            return RegistryEntryList.of(mapper, value);
        }
        List<RegistryEntry<T>> list = new ObjectArrayList<>(registryEntryList.iterator());
        list.add(mapper.apply(value));
        return RegistryEntryList.of(list);
    }

    public static <E, T> RegistryEntryList<T> mergeValues(RegistryEntryList<T> registryEntryList, Function<E, RegistryEntry<T>> mapper, Collection<E> values) {
        if(registryEntryList.size() == 0) {
            return RegistryEntryList.of(mapper, values);
        }
        List<RegistryEntry<T>> list = new ObjectArrayList<>(registryEntryList.iterator());
        values.forEach(value -> list.add(mapper.apply(value)));
        return RegistryEntryList.of(list);
    }

    public static <T> RegistryEntryList<T> mergeValues(RegistryEntryList<T> first, RegistryEntryList<T> second) {
        if(first.size() == 0) {
            return second;
        }else if(second.size() == 0) {
            return first;
        }
        List<RegistryEntry<T>> list = new ObjectArrayList<>(first.iterator());
        second.forEach(list::add);
        return RegistryEntryList.of(list);
    }

}
