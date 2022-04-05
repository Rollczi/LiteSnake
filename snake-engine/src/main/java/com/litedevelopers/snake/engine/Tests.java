package com.litedevelopers.snake.engine;

import com.litedevelopers.snake.engine.math.Direction;
import com.litedevelopers.snake.engine.snake.SnakeGrid;

public class Tests {

    public static void main(String[] args) {
        SnakeGrid snake = new SnakeGrid("Kamil Ślimak", 1);

        System.out.println(snake.getHead().getCenter());
        snake.move(1.0D, Direction.DOWN);
        snake.move(1.0D, Direction.DOWN);

        System.out.println(snake);

        snake.move(1.0D, Direction.RIGHT);

        System.out.println(snake);

        snake.move(1.0D,Direction.UP);

        System.out.println(snake);
    }
}
