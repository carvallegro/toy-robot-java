package com.carvallegro.toyrobot.cli;

import com.carvallegro.toyrobot.logic.Coordinates;
import com.carvallegro.toyrobot.logic.Direction;
import com.carvallegro.toyrobot.logic.Grid;

public class TextInterface {
    public static final String COMMAND_UNKNOWN = "Command unknown";
    public static final String INVALID_PLACE_PARAMETERS = "The PLACE command must have three parameters: X,Y,DIRECTION";
    public static final String INVALID_COORDINATES_PARAMETERS = "Coordinates must be integers";
    public static final int DIRECTION_POSITION = 2;
    public static final int X_POSITION = 0;
    public static final int Y_POSITION = 1;

    private Grid grid;

    public TextInterface() {
        grid = new Grid();
    }

    public String command(String stringCommand, String[] params) {
        Command command = Command.find(stringCommand);
        switch (command) {
            case PLACE:
                callPlace(params);
                break;
            case MOVE:
                grid.move();
                break;
            case LEFT:
                grid.left();
                break;
            case RIGHT:
                grid.right();
                break;
            case REPORT:
                return grid.report();
            case UNKNOWN:
            default:
                return COMMAND_UNKNOWN;
        }

        return null;
    }

    private void callPlace(String[] params) {
        if (params == null) {
            throw new IllegalArgumentException(INVALID_PLACE_PARAMETERS);
        }
        if (params.length != 3) {
            throw new IllegalArgumentException(INVALID_PLACE_PARAMETERS);
        }

        Coordinates coordinates = getCoordinatesFromParams(params);
        Direction direction = Direction.find(params[DIRECTION_POSITION]);
        grid.place(coordinates, direction);
    }

    private static Coordinates getCoordinatesFromParams(String[] params) {
        Integer x;
        Integer y;
        try {
            x = Integer.valueOf(params[X_POSITION]);
            y = Integer.valueOf(params[Y_POSITION]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_COORDINATES_PARAMETERS);
        }
        return new Coordinates(x, y);
    }

    public static void main(String[] args) {
        TextInterface textInterface = new TextInterface();
    }
}
