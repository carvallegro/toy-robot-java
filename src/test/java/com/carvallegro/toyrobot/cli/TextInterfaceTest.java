package com.carvallegro.toyrobot.cli;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TextInterfaceTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    private TextInterface textInterface;

    @BeforeEach
    public void beforeEach() {
        textInterface = new TextInterface();
    }

    @BeforeAll
    public static void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restore() {
        System.setOut(originalOut);
    }

    @Test
    void runCommand_throw_exception_when_robot_have_not_been_placed() {
        // Given
        String command = "move";

        // When
        Throwable exception = assertThrows(IllegalStateException.class, () -> textInterface.runCommand(command, null));

        // Then
        assertEquals("Robot hasn't been placed", exception.getMessage());
    }

    @Test
    void runCommand_throw_exception_when_PLACE_has_null_arguments() {
        // Given
        String command = "place";

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> textInterface.runCommand(command, null));

        // Then
        assertEquals("The PLACE command must have three parameters: X,Y,DIRECTION", exception.getMessage());
    }

    @Test
    void runCommand_throw_exception_when_PLACE_has_invalid_number_of_arguments() {
        // Given
        String command = "place";
        String[] params = new String[]{"a", "b"};

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> textInterface.runCommand(command, params));

        // Then
        assertEquals("The PLACE command must have three parameters: X,Y,DIRECTION", exception.getMessage());
    }

    @Test
    void runCommand_throw_exception_when_PLACE_has_invalid_coordinate_params() {
        // Given
        String command = "place";
        String[] params = new String[]{"a", "b", "c"};

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> textInterface.runCommand(command, params));

        // Then
        assertEquals("Coordinates must be integers", exception.getMessage());
    }

    @Test
    void runCommand_REPORT_should_print_robot_state() {
        // Given
        String[] params = new String[]{"0", "0", "NORTH"};
        String expected = "0,0,NORTH";

        // When
        textInterface.runCommand(Command.PLACE.name(), params);
        textInterface.runCommand(Command.REPORT.name(), null);
        String actual = outContent.toString();

        // Then
        assertEquals(actual.trim(), expected);
    }

    @Test
    void runCommand_LEFT_should_print_nothing() {
        // Given
        String[] params = new String[]{"0", "0", "NORTH"};

        // When
        textInterface.runCommand(Command.PLACE.name(), params);
        textInterface.runCommand(Command.LEFT.name(), null);

        // Then
        assertEquals("", outContent.toString());
    }

    @Test
    void runCommand_RIGHT_should_print_nothing() {
        // Given
        String[] params = new String[]{"0", "0", "NORTH"};

        // When
        textInterface.runCommand(Command.PLACE.name(), params);
        textInterface.runCommand(Command.RIGHT.name(), null);

        // Then
        assertEquals("", outContent.toString());
    }

    @Test
    void runCommand_MOVE_should_print_nothing() {
        // Given
        String[] params = new String[]{"0", "0", "NORTH"};

        // When
        textInterface.runCommand(Command.PLACE.name(), params);
        textInterface.runCommand(Command.MOVE.name(), null);

        // Then
        assertEquals("", outContent.toString());
    }
}