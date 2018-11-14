package com.carvallegro.toyrobot;

import com.carvallegro.toyrobot.cli.TextInterface;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSuite {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    private TextInterface textInterface;

    @BeforeEach
    public void beforeEach() {
        textInterface = new TextInterface();
        outContent.reset();
    }

    @BeforeAll
    public static void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restore() {
        System.setOut(originalOut);
    }

    private String getStringPathFromTestResources(String filename) {
        try {
            return Path.of(getClass().getClassLoader().getResource(filename).toURI()).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return filename;
    }

    private String getStringContentFromResources(String filename) {
        try {
            String uri = getStringPathFromTestResources(filename);
            return Files.readString(Path.of(uri));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String getSystemOutput() {
        return outContent.toString().trim();
    }

    @Test
    public void app_should_complain_when_no_argument_is_given() {
        // Given
        String[] args = {};

        // When
        TextInterface.main(args);

        // Then
        assertEquals("One argument expected: path to the input file.", getSystemOutput());
    }

    @Test
    public void app_should_not_find_file() {
        // Given
        String url = "not_an_actual_file";

        // When
        TextInterface.main(new String[]{url});

        // Then
        assertEquals("File not found", getSystemOutput());
    }

    @Test
    public void app_should_fulfill_test_a() {
        // Given
        String input = getStringPathFromTestResources("test_a.input.txt");
        String expected = getStringContentFromResources("test_a.expected.txt");

        // When
        TextInterface.main(new String[]{input});

        // Then
        assertEquals(expected, getSystemOutput());
    }

    @Test
    public void app_should_fulfill_test_b() {
        // Given
        String input = getStringPathFromTestResources("test_b.input.txt");
        String expected = getStringContentFromResources("test_b.expected.txt");

        // When
        TextInterface.main(new String[]{input});

        // Then
        assertEquals(expected, getSystemOutput());
    }

    @Test
    public void app_should_fulfill_test_c() {
        // Given
        String input = getStringPathFromTestResources("test_c.input.txt");
        String expected = getStringContentFromResources("test_c.expected.txt");

        // When
        TextInterface.main(new String[]{input});

        // Then
        assertEquals(expected, getSystemOutput());
    }

    @Test
    public void app_should_fulfill_test_all_around() {
        // Given
        String input = getStringPathFromTestResources("test_all_around.input.txt");
        String expected = getStringContentFromResources("test_all_around.expected.txt");

        // When
        TextInterface.main(new String[]{input});

        // Then
        assertEquals(expected, getSystemOutput());
    }

    @Test
    public void app_should_fulfill_test_never_fall() {
        // Given
        String input = getStringPathFromTestResources("test_never_fall.input.txt");
        String expected = getStringContentFromResources("test_never_fall.expected.txt");

        // When
        TextInterface.main(new String[]{input});

        // Then
        assertEquals(expected, getSystemOutput());
    }

    @Test
    public void app_should_fulfill_test_no_invalid_commands() {
        // Given
        String input = getStringPathFromTestResources("test_no_invalid_commands.input.txt");
        String expected = getStringContentFromResources("test_no_invalid_commands.expected.txt");

        // When
        TextInterface.main(new String[]{input});

        // Then
        assertEquals(expected, getSystemOutput());
    }

    @Test
    public void app_should_fulfill_test_not_in_place() {
        // Given
        String input = getStringPathFromTestResources("test_not_in_place.input.txt");
        String expected = getStringContentFromResources("test_not_in_place.expected.txt");

        // When
        TextInterface.main(new String[]{input});

        // Then
        assertEquals(expected, getSystemOutput());
    }

    @Test
    public void app_should_fulfill_test_rotate_left() {
        // Given
        String input = getStringPathFromTestResources("test_rotate_left.input.txt");
        String expected = getStringContentFromResources("test_rotate_left.expected.txt");

        // When
        TextInterface.main(new String[]{input});

        // Then
        assertEquals(expected, getSystemOutput());
    }

    @Test
    public void app_should_fulfill_test_rotate_right() {
        // Given
        String input = getStringPathFromTestResources("test_rotate_right.input.txt");
        String expected = getStringContentFromResources("test_rotate_right.expected.txt");

        // When
        TextInterface.main(new String[]{input});

        // Then
        assertEquals(expected, getSystemOutput());
    }


}
