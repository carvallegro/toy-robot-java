package com.carvallegro.toyrobot.logic;

public class Robot {
    private Direction direction;
    private Coordinates coordinates;

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return coordinates.getX() + "," + coordinates.getY() + "," + direction.name();
    }
}
