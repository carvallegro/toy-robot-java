package com.carvallegro.toyrobot.logic;

public class Grid {
    private static final String ROBOT_NOT_PLACED_ERROR_MESSAGE = "Robot hasn't been placed";
    private static final String ROBOT_WILL_FALL_ERROR_MESSAGE = "The robot will fall";
    private static final String NO_COORDINATES_ERROR_MESSAGE = "No Coordinates are set";
    private static final String INVALID_COORDINATES_ERROR_MESSAGE = "Coordinates are invalid";
    private static final String NO_DIRECTION_ERROR_MESSAGE = "No direction is set";

    private final static int GRID_HEIGHT = 5;
    private final static int GRID_WIDTH = 5;
    private static final int COORDINATE_MIN_VALUE = 0;

    private Robot robot;

    public Grid() {
        robot = new Robot();
    }

    public void place(Coordinates coordinates, Direction direction) {
        if (coordinates == null) {
            throw new IllegalArgumentException(NO_COORDINATES_ERROR_MESSAGE);
        }

        if (!isValidCoordinate(coordinates)) {
            throw new IllegalArgumentException(INVALID_COORDINATES_ERROR_MESSAGE);
        }

        if (direction == null) {
            throw new IllegalArgumentException(NO_DIRECTION_ERROR_MESSAGE);
        }

        robot.setCoordinates(coordinates);
        robot.setDirection(direction);
    }

    private static boolean isValidCoordinate(Coordinates coordinates) {
        Integer x = coordinates.getX();
        if (x == null) return false;
        if (x < COORDINATE_MIN_VALUE) return false;
        if (x >= GRID_WIDTH) return false;

        Integer y = coordinates.getY();
        if (y == null) return false;
        if (y < COORDINATE_MIN_VALUE) return false;
        if (y >= GRID_HEIGHT) return false;

        return true;
    }

    public void move() {
        if(!isRobotPlaced()){
            throw new IllegalStateException(ROBOT_NOT_PLACED_ERROR_MESSAGE);
        }

        Coordinates nextCoordinates = robot.getDirection().moveTowards(robot.getCoordinates());
        if(!isValidCoordinate(nextCoordinates)){
            throw new IllegalStateException(ROBOT_WILL_FALL_ERROR_MESSAGE);
        }

        robot.setCoordinates(nextCoordinates);
    }

    private boolean isRobotPlaced() {
        return robot.getCoordinates() != null;
    }

    public void left(){
        if(!isRobotPlaced()){
            throw new IllegalStateException(ROBOT_NOT_PLACED_ERROR_MESSAGE);
        }

        Direction newDirection = robot.getDirection().left();
        robot.setDirection(newDirection);
    }

    public void right(){
        if(!isRobotPlaced()){
            throw new IllegalStateException(ROBOT_NOT_PLACED_ERROR_MESSAGE);
        }

        Direction newDirection = robot.getDirection().right();
        robot.setDirection(newDirection);
    }

    public String report(){
        if(!isRobotPlaced()){
            return ROBOT_NOT_PLACED_ERROR_MESSAGE;
        }

        return robot.toString();
    }

    public Coordinates getRobotCoordinates() {
        try {
            return robot.getCoordinates().clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public Direction getRobotDirection() {
        return robot.getDirection();
    }
}
