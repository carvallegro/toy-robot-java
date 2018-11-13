package com.carvallegro.toyrobot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void place_should_throw_IllegalArgumentException_when_no_coordinate_are_set() {
        // Given
        Grid grid = new Grid();

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> grid.place(null, null));

        // Then
        assertEquals("No Coordinates are set", exception.getMessage());
    }

    @Test
    void place_should_throw_IllegalArgumentException_when_coordinate_do_not_have_x_value() {
        // Given
        Grid grid = new Grid();
        Coordinates initialCoordinates = new Coordinates(null, 0);

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> grid.place(initialCoordinates, null));

        // Then
        assertEquals("Coordinates are invalid", exception.getMessage());
    }

    @Test
    void place_should_throw_IllegalArgumentException_when_coordinate_do_not_have_y_value() {
        // Given
        Grid grid = new Grid();
        Coordinates initialCoordinates = new Coordinates(0, null);

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> grid.place(initialCoordinates, null));

        // Then
        assertEquals("Coordinates are invalid", exception.getMessage());
    }

    @Test
    void place_should_throw_IllegalArgumentException_when_x_is_negative() {
        // Given
        Grid grid = new Grid();
        Coordinates initialCoordinates = new Coordinates(-1, 1);

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> grid.place(initialCoordinates, null));

        // Then
        assertEquals("Coordinates are invalid", exception.getMessage());
    }

    @Test
    void place_should_throw_IllegalArgumentException_when_y_is_negative() {
        // Given
        Grid grid = new Grid();
        Coordinates initialCoordinates = new Coordinates(0, -1);

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> grid.place(initialCoordinates, null));

        // Then
        assertEquals("Coordinates are invalid", exception.getMessage());
    }

    @Test
    void place_should_throw_IllegalArgumentException_when_x_is_out_of_width_bounds() {
        // Given
        Grid grid = new Grid();
        Coordinates initialCoordinates = new Coordinates(10000, 1);

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> grid.place(initialCoordinates, null));

        // Then
        assertEquals("Coordinates are invalid", exception.getMessage());
    }

    @Test
    void place_should_throw_IllegalArgumentException_when_y_is_out_of_height_bounds() {
        // Given
        Grid grid = new Grid();
        Coordinates initialCoordinates = new Coordinates(1, 10000);

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> grid.place(initialCoordinates, null));

        // Then
        assertEquals("Coordinates are invalid", exception.getMessage());
    }

    @Test
    void place_should_throw_IllegalArgumentException_when_no_direction_is_set() {
        // Given
        Grid grid = new Grid();
        Coordinates initialCoordinates = new Coordinates(0, 0);

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> grid.place(initialCoordinates, null));

        // Then
        assertEquals("No direction is set", exception.getMessage());
    }

    @Test
    void place_should_set_Robot_to_appropriate_coordinates_and_direction() {
        // Given
        Grid grid = new Grid();
        Coordinates initialCoordinates = new Coordinates(0, 0);
        Direction direction = Direction.EAST;

        // When
        grid.place(initialCoordinates, direction);

        // Then
        assertEquals(grid.getRobotCoordinates(), initialCoordinates);
        assertEquals(grid.getRobotDirection(), direction);
    }

    @Test
    void move_should_throw_IllegalStateException_if_robot_has_not_been_placed() {
        // Given
        Grid grid = new Grid();

        // When
        Throwable exception = assertThrows(IllegalStateException.class, () -> grid.move());

        // Then
        assertEquals("Robot hasn't been placed", exception.getMessage());
    }

    @Test
    public void move_should_throw_IllegalStateException_if_the_robot_is_facing_the_western_edge() {
        // Given
        Grid grid = new Grid();
        Coordinates initialCoordinates = new Coordinates(0, 0);
        Direction direction = Direction.WEST;

        // When
        grid.place(initialCoordinates, direction);
        Throwable exception = assertThrows(IllegalStateException.class, () -> grid.move());

        // Then
        assertEquals("The robot will fall", exception.getMessage());
        assertEquals(initialCoordinates, grid.getRobotCoordinates());
    }

    @Test
    public void move_should_throw_IllegalStateException_if_the_robot_is_facing_the_southern_edge() {
        // Given
        Grid grid = new Grid();
        Coordinates initialCoordinates = new Coordinates(0, 0);
        Direction direction = Direction.SOUTH;

        // When
        grid.place(initialCoordinates, direction);
        Throwable exception = assertThrows(IllegalStateException.class, () -> grid.move());

        // Then
        assertEquals("The robot will fall", exception.getMessage());
        assertEquals(initialCoordinates, grid.getRobotCoordinates());
    }

    @Test
    public void move_should_throw_IllegalStateException_if_the_robot_is_facing_the_eastern_edge() {
        // Given
        Grid grid = new Grid();
        Coordinates initialCoordinates = new Coordinates(4, 0);
        Direction direction = Direction.EAST;

        // When
        grid.place(initialCoordinates, direction);
        Throwable exception = assertThrows(IllegalStateException.class, () -> grid.move());

        // Then
        assertEquals("The robot will fall", exception.getMessage());
        assertEquals(initialCoordinates, grid.getRobotCoordinates());
    }

    @Test
    public void move_should_throw_IllegalStateException_if_the_robot_is_facing_the_northern_edge() {
        // Given
        Grid grid = new Grid();
        Coordinates initialCoordinates = new Coordinates(4, 4);
        Direction direction = Direction.NORTH;

        // When
        grid.place(initialCoordinates, direction);
        Throwable exception = assertThrows(IllegalStateException.class, () -> grid.move());

        // Then
        assertEquals("The robot will fall", exception.getMessage());
        assertEquals(initialCoordinates, grid.getRobotCoordinates());
    }

    @Test
    public void move_should_move_the_robot_towards_the_east_when_facing_east() {
        // Given
        Grid grid = new Grid();
        Coordinates coordinates = new Coordinates(0, 0);
        Direction direction = Direction.EAST;
        Coordinates expected = new Coordinates(1, 0);

        // When
        grid.place(coordinates, direction);
        grid.move();

        // Then
        assertEquals(expected, grid.getRobotCoordinates());
    }

    @Test
    void left_should_throw_IllegalStateException_if_robot_has_not_been_placed() {
        // Given
        Grid grid = new Grid();

        // When
        Throwable exception = assertThrows(IllegalStateException.class, () -> grid.left());

        // Then
        assertEquals("Robot hasn't been placed", exception.getMessage());
    }

    @Test
    public void left_should_rotate_the_robot_to_the_west_when_facing_north() {
        // Given
        Grid grid = new Grid();
        Coordinates coordinates = new Coordinates(0, 0);
        Direction direction = Direction.NORTH;
        Direction expected = Direction.WEST;

        // When
        grid.place(coordinates, direction);
        grid.left();

        // Then
        assertEquals(expected, grid.getRobotDirection());
    }


    @Test
    void right_should_throw_IllegalStateException_if_robot_has_not_been_placed() {
        // Given
        Grid grid = new Grid();

        // When
        Throwable exception = assertThrows(IllegalStateException.class, () -> grid.right());

        // Then
        assertEquals("Robot hasn't been placed", exception.getMessage());
    }

    @Test
    public void right_should_rotate_the_robot_to_the_east_when_facing_north() {
        // Given
        Grid grid = new Grid();
        Coordinates coordinates = new Coordinates(0, 0);
        Direction direction = Direction.NORTH;
        Direction expected = Direction.EAST;

        // When
        grid.place(coordinates, direction);
        grid.right();

        // Then
        assertEquals(expected, grid.getRobotDirection());
    }


    @Test
    void report_should_throw_IllegalStateException_if_robot_has_not_been_placed() {
        // Given
        Grid grid = new Grid();

        // When
        Throwable exception = assertThrows(IllegalStateException.class, () -> grid.report());

        // Then
        assertEquals("Robot hasn't been placed", exception.getMessage());
    }

    @Test
    void report_should_return_robot_position_and_facing_direction() {
        // Given
        Grid grid = new Grid();
        Coordinates coordinates = new Coordinates(0, 0);
        Direction direction = Direction.NORTH;
        String expected = "0,0,NORTH";

        // When
        grid.place(coordinates, direction);
        String actual = grid.report();

        // Then
        assertEquals(expected, actual);
    }
}