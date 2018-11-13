package com.carvallegro.toyrobot.cli;

public enum Command {
    PLACE,
    MOVE,
    LEFT,
    RIGHT,
    REPORT,
    UNKNOWN;

    public static Command find(String command){
        if(command == null || command.isBlank()){
            return UNKNOWN;
        }

        String sanitizedCommand = command.toUpperCase();
        for (Command c: Command.values()) {
            if(c.name().equals(sanitizedCommand)){
                return c;
            }
        }

        return UNKNOWN;
    }
}
