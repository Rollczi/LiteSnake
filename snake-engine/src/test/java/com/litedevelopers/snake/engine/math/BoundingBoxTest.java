package com.litedevelopers.snake.engine.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoundingBoxTest {

    @Test
    void containsCross() {
        BoundingBox box = new BoundingBox(new Position(1, 1), new Position(10, 10));

        assertTrue(box.contains(new BoundingBox(new Position(1, 1), new Position(10, 10))));
    }

    @Test
    void containsIn() {
        BoundingBox box = new BoundingBox(new Position(1, 1), new Position(10, 10));

        assertTrue(box.contains(new BoundingBox(new Position(2, 2), new Position(8, 8))));
    }

    @Test
    void containsOut() {
        BoundingBox box = new BoundingBox(new Position(1, 1), new Position(10, 10));

        assertTrue(box.contains(new BoundingBox(new Position(0, 0), new Position(81, 81))));
    }

    @Test
    void wallPoint1() {
        BoundingBox box = new BoundingBox(new Position(0, 0), new Position(10, 10));
        Position nearestPoint = box.nearestPoint(new Position(1, 2));

        assertEquals(0, nearestPoint.x);
        assertEquals(2, nearestPoint.y);
    }

    @Test
    void wallPoint2() {
        BoundingBox box = new BoundingBox(new Position(0, 0), new Position(10, 10));
        Position nearestPoint = box.nearestPoint(new Position(3, 2));

        assertEquals(3, nearestPoint.x);
        assertEquals(0, nearestPoint.y);
    }


}
