package com.eightsidedsquare.zine.client.util;

public enum ConnectedShape {
    ALL, CORNER, HORIZONTAL, NONE, VERTICAL;

    public static ConnectedShape fromChar(char c) {
        return switch (c) {
            case 'A', 'a' -> ALL;
            case 'C', 'c' -> CORNER;
            case 'H', 'h' -> HORIZONTAL;
            case 'V', 'v' -> VERTICAL;
            default -> NONE;
        };
    }
}