package com.litedevelopers.snake.engine;

import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.SnakeGrid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeGridTest {

    @Test
    public void moveTest() {
        SnakeGrid snake = new SnakeGrid("Kamil Ślimak", 1);

        snake.moveWithApple(new Position(1.5,0.3));
        snake.move(new Position(1.5, 1.4));
        snake.moveWithApple(new Position(2.5, 1.6));
        snake.move(new Position(3.3, 1.6));

        assertEquals(snake.getHeadPosition().getX(), 3.3);
        assertEquals(snake.getHeadPosition().getY(), 1.6);

        assertEquals(snake.getLength(), 3);
    }

}
