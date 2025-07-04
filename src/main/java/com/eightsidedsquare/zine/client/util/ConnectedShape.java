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

    public enum Operation {
        AND_ALL, AND_CORNER, AND_HORIZONTAL, AND_NONE, AND_VERTICAL, 
        OR_ALL, OR_CORNER, OR_HORIZONTAL, OR_NONE, OR_VERTICAL;

        public static Operation and(ConnectedShape shape) {
            return switch (shape) {
                case ALL -> AND_ALL;
                case CORNER -> AND_CORNER;
                case HORIZONTAL -> AND_HORIZONTAL;
                case NONE -> AND_NONE;
                case VERTICAL -> AND_VERTICAL;
            };
        }

        public static Operation or(ConnectedShape shape) {
            return switch (shape) {
                case ALL -> OR_ALL;
                case CORNER -> OR_CORNER;
                case HORIZONTAL -> OR_HORIZONTAL;
                case NONE -> OR_NONE;
                case VERTICAL -> OR_VERTICAL;
            };
        }

        public ConnectedShape apply(ConnectedShape shape) {
            return switch (this) {
                case AND_ALL, OR_NONE -> shape;
                case AND_CORNER -> shape == ConnectedShape.NONE ? ConnectedShape.NONE : ConnectedShape.CORNER;
                case AND_HORIZONTAL -> switch (shape) {
                    case NONE -> ConnectedShape.NONE;
                    case HORIZONTAL, ALL -> ConnectedShape.HORIZONTAL;
                    default -> ConnectedShape.CORNER;
                };
                case AND_NONE -> ConnectedShape.NONE;
                case AND_VERTICAL -> switch (shape) {
                    case NONE -> ConnectedShape.NONE;
                    case VERTICAL, ALL -> ConnectedShape.VERTICAL;
                    default -> ConnectedShape.CORNER;
                };
                case OR_ALL -> ConnectedShape.ALL;
                case OR_CORNER -> shape == ConnectedShape.NONE ? ConnectedShape.CORNER : shape;
                case OR_HORIZONTAL -> switch (shape) {
                    case VERTICAL, ALL -> ConnectedShape.ALL;
                    default -> ConnectedShape.HORIZONTAL;
                };
                case OR_VERTICAL -> switch (shape) {
                    case HORIZONTAL, ALL -> ConnectedShape.ALL;
                    default -> ConnectedShape.VERTICAL;
                };
            };
        }

    }
}