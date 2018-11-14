package com.carvallegro.toyrobot.cli;

import com.carvallegro.toyrobot.logic.Coordinates;
import com.carvallegro.toyrobot.logic.Direction;
import com.carvallegro.toyrobot.logic.Grid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TextInterface {
    private static final String INVALID_PLACE_PARAMETERS = "The PLACE command must have three parameters: X,Y,DIRECTION";
    private static final String INVALID_COORDINATES_PARAMETERS = "Coordinates must be integers";
    private static final int DIRECTION_POSITION = 2;
    private static final int X_POSITION = 0;
    private static final int Y_POSITION = 1;

    private final Grid grid;

    public TextInterface() {
        grid = new Grid();
    }

    public void runCommand(String commandAsString, String[] params) {
        Command command = Command.find(commandAsString);
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
                displayMessage(grid.report());
                break;
            case UNKNOWN:
            default:
                break;
        }
    }

    private void callPlace(String[] locationParams) {
        if (locationParams == null) {
            throw new IllegalArgumentException(INVALID_PLACE_PARAMETERS);
        }
        if (locationParams.length != 3) {
            throw new IllegalArgumentException(INVALID_PLACE_PARAMETERS);
        }

        Coordinates coordinates = getCoordinatesFromParams(locationParams);
        Direction direction = Direction.find(locationParams[DIRECTION_POSITION]);
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
        if (args.length < 1) {
            System.out.println("One argument expected: path to the input file.");
            return;
        }

        List<String> inputLines = getLinesFromFiles(args);
        if (inputLines == null) {
            System.out.println("File not found");
            return;
        }

        TextInterface textInterface = new TextInterface();
        for (String line : inputLines) {
            String[] lineAsArray = line.trim().split(" ");

            String command = lineAsArray[0];

            String[] params = getCommandParams(lineAsArray);
            try {
                textInterface.runCommand(command, params);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static String[] getCommandParams(String[] lineAsArray) {
        if (lineAsArray.length < 2) {
            return null;
        }

        return lineAsArray[1].split(",");
    }

    private static List<String> getLinesFromFiles(String[] args) {
        try {
            return Files.readAllLines(Path.of(args[0]));
        } catch (IOException e) {
            System.err.println("Trying to find file: " + args[0]);
            e.printStackTrace();
        }
        return null;
    }

    private static void displayMessage(String message) {
        System.out.println(message);
    }
}
