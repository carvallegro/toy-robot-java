package com.carvallegro.toyrobot;

public enum Direction {
    NORTH(new Coordinates(0, 1)),
    EAST(new Coordinates(1, 0)),
    SOUTH(new Coordinates(0, -1)),
    WEST(new Coordinates(-1, 0));

    private final Coordinates lambda;

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

    Direction(Coordinates lambda) {
        this.lambda = lambda;
    }

    public Direction left() {
        return left;
    }

    public Direction right() {
        return right;
    }

    public Coordinates moveTowards(Coordinates coordinates) {
        int newX = coordinates.getX() + 1 * lambda.getX();
        int newY = coordinates.getY() + 1 * lambda.getY();
        return new Coordinates(newX, newY);
    }
}
