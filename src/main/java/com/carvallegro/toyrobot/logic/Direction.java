package com.carvallegro.toyrobot.logic;

public enum Direction {
    NORTH(new Coordinates(0, 1)),
    EAST(new Coordinates(1, 0)),
    SOUTH(new Coordinates(0, -1)),
    WEST(new Coordinates(-1, 0));

    private final Coordinates delta;

    private Direction left;

    static {
        NORTH.left = WEST;
        EAST.left = NORTH;
        SOUTH.left = EAST;
        WEST.left = SOUTH;
    }

    private Direction right;

    static {
        NORTH.right = EAST;
        EAST.right = SOUTH;
        SOUTH.right = WEST;
        WEST.right = NORTH;
    }

    Direction(Coordinates delta) {
        this.delta = delta;
    }

    public Direction left() {
        return left;
    }

    public Direction right() {
        return right;
    }

    public Coordinates moveTowards(Coordinates coordinates) {
        int newX = coordinates.getX() + 1 * delta.getX();
        int newY = coordinates.getY() + 1 * delta.getY();
        return new Coordinates(newX, newY);
    }

    public static Direction find(String stringDirection) {
        if (stringDirection == null || stringDirection.isBlank()) {
            return null;
        }

        String sanitizedCommand = stringDirection.toUpperCase();
        for (Direction direction : Direction.values()) {
            if (direction.name().equals(sanitizedCommand)) {
                return direction;
            }
        }

        return null;
    }
}
