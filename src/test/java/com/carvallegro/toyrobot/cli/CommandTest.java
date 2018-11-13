package com.carvallegro.toyrobot.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void find_should_return_the_correct_command() {
        // Given
        String command = "LEFT";
        Command expected = Command.LEFT;

        // When
        Command actual = Command.find(command);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void find_should_return_UNKNOWN_when_string_is_empty() {
        // Given
        String command = "";
        Command expected = Command.UNKNOWN;

        // When
        Command actual = Command.find(command);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void find_should_return_UNKNOWN_when_command_is_unknown() {
        // Given
        String command = "not a command";
        Command expected = Command.UNKNOWN;

        // When
        Command actual = Command.find(command);

        // Then
        assertEquals(expected, actual);
    }
}