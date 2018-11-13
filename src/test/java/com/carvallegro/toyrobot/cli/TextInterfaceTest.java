package com.carvallegro.toyrobot.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextInterfaceTest {

    @Test
    void command_returns_message_when_command_is_unknown() {
        // Given
        TextInterface textInterface = new TextInterface();
        String command = "random";
        String expected = "Command unknown";

        // When
        String actual = textInterface.command(command, null);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void command_throw_exception_when_robot_have_not_been_placed() {
        // Given
        TextInterface textInterface = new TextInterface();
        String command = "move";

        // When
        Throwable exception = assertThrows(IllegalStateException.class, () -> textInterface.command(command, null));

        // Then
        assertEquals("Robot hasn't been placed", exception.getMessage());
    }

    @Test
    void command_throw_exception_when_PLACE_has_null_arguments() {
        // Given
        TextInterface textInterface = new TextInterface();
        String command = "place";

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> textInterface.command(command, null));

        // Then
        assertEquals("The PLACE command must have three parameters: X,Y,DIRECTION", exception.getMessage());
    }

    @Test
    void command_throw_exception_when_PLACE_has_invalid_number_of_arguments() {
        // Given
        TextInterface textInterface = new TextInterface();
        String command = "place";
        String[] params = new String[]{"a", "b"};

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> textInterface.command(command, params));

        // Then
        assertEquals("The PLACE command must have three parameters: X,Y,DIRECTION", exception.getMessage());
    }

    @Test
    void command_throw_exception_when_PLACE_has_invalid_coordinate_params() {
        // Given
        TextInterface textInterface = new TextInterface();
        String command = "place";
        String[] params = new String[]{"a", "b", "c"};

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> textInterface.command(command, params));

        // Then
        assertEquals("Coordinates must be integers", exception.getMessage());
    }

    @Test
    void command_REPORT_should_give_robot_state() {
        // Given
        TextInterface textInterface = new TextInterface();
        String[] params = new String[]{"0", "0", "NORTH"};
        String expected = "0,0,NORTH";

        // When
        textInterface.command(Command.PLACE.name(), params);
        String actual = textInterface.command(Command.REPORT.name(), null);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void command_LEFT_should_return_null() {
        // Given
        TextInterface textInterface = new TextInterface();
        String[] params = new String[]{"0", "0", "NORTH"};

        // When
        textInterface.command(Command.PLACE.name(), params);
        String actual = textInterface.command(Command.LEFT.name(), null);

        // Then
        assertNull(actual);
    }

    @Test
    void command_RIGHT_should_return_null() {
        // Given
        TextInterface textInterface = new TextInterface();
        String[] params = new String[]{"0", "0", "NORTH"};

        // When
        textInterface.command(Command.PLACE.name(), params);
        String actual = textInterface.command(Command.RIGHT.name(), null);

        // Then
        assertNull(actual);
    }

    @Test
    void command_MOVE_should_return_null() {
        // Given
        TextInterface textInterface = new TextInterface();
        String[] params = new String[]{"0", "0", "NORTH"};

        // When
        textInterface.command(Command.PLACE.name(), params);
        String actual = textInterface.command(Command.LEFT.name(), null);

        // Then
        assertNull(actual);
    }
}