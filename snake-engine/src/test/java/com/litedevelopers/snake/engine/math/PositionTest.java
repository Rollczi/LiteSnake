package com.litedevelopers.snake.engine.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    void angle() {
        assertEquals(45, new Position(0, 1).angle(Position.ONE));
    }
}