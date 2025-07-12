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

    public ConnectedShape and(ConnectedShape shape) {
        return switch (this) {
            case ALL -> shape;
            case CORNER -> shape == ConnectedShape.NONE ? ConnectedShape.NONE : ConnectedShape.CORNER;
            case HORIZONTAL -> switch (shape) {
                case NONE -> ConnectedShape.NONE;
                case HORIZONTAL, ALL -> ConnectedShape.HORIZONTAL;
                default -> ConnectedShape.CORNER;
            };
            case NONE -> NONE;
            case VERTICAL -> switch (shape) {
                case NONE -> ConnectedShape.NONE;
                case VERTICAL, ALL -> ConnectedShape.VERTICAL;
                default -> ConnectedShape.CORNER;
            };
        };
    }

    public ConnectedShape or(ConnectedShape shape) {
        return switch (this) {
            case ALL -> ALL;
            case CORNER -> shape == ConnectedShape.NONE ? ConnectedShape.CORNER : shape;
            case HORIZONTAL -> switch (shape) {
                case VERTICAL, ALL -> ConnectedShape.ALL;
                default -> ConnectedShape.HORIZONTAL;
            };
            case NONE -> shape;
            case VERTICAL -> switch (shape) {
                case HORIZONTAL, ALL -> ConnectedShape.ALL;
                default -> ConnectedShape.VERTICAL;
            };
        };
    }

}