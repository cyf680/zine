package com.eightsidedsquare.zine.common.util;

import java.util.Arrays;

public enum Offset {
    NORTH(0, 0, -1),
    EAST(1, 0, 0),
    SOUTH(0, 0, 1),
    WEST(-1, 0, 0),
    UP(0, 1, 0),
    DOWN(0, -1, 0),
    NORTHEAST(1, 0, -1),
    SOUTHEAST(1, 0, 1),
    SOUTHWEST(-1, 0, 1),
    NORTHWEST(-1, 0, -1),
    UP_NORTH(0, 1, -1),
    UP_EAST(1, 1, 0),
    UP_SOUTH(0, 1, 1),
    UP_WEST(-1, 1, 0),
    DOWN_NORTH(0, -1, -1),
    DOWN_EAST(1, -1, 0),
    DOWN_SOUTH(0, -1, 1),
    DOWN_WEST(-1, -1, 0),
    UP_NORTHEAST(1, 1, -1),
    UP_SOUTHEAST(1, 1, 1),
    UP_SOUTHWEST(-1, 1, 1),
    UP_NORTHWEST(-1, 1, -1),
    DOWN_NORTHEAST(1, -1, -1),
    DOWN_SOUTHEAST(1, -1, 1),
    DOWN_SOUTHWEST(-1, -1, 1),
    DOWN_NORTHWEST(-1, -1, -1);

    private static final Offset[] VALUES_DIST_1 = {NORTH, EAST, SOUTH, WEST, UP, DOWN};
    private static final Offset[] VALUES_DIST_2 = createDist2Values();

    private final int x;
    private final int y;
    private final int z;

    Offset(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private static Offset[] createDist2Values() {
        return Arrays.stream(values())
                .filter(offset -> Math.abs(offset.getX()) + Math.abs(offset.getY()) + Math.abs(offset.getZ()) < 3)
                .toArray(Offset[]::new);
    }

    public static Offset[] values(int distance) {
        return distance == 1 ? VALUES_DIST_1 : distance == 2 ? VALUES_DIST_2 : values();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }
}
