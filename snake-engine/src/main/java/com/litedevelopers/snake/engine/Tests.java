package com.litedevelopers.snake.engine;

import com.litedevelopers.snake.engine.math.Direction;
import com.litedevelopers.snake.engine.snake.SnakeGrid;

public class Tests {

    public static void main(String[] args) {
        SnakeGrid snake = new SnakeGrid("Kamil Ślimak", 1);

        System.out.println(snake.getHead().getCenter());
        snake.move(Direction.DOWN);
        snake.move(Direction.DOWN);

        System.out.println(snake);

        snake.move(Direction.RIGHT);
        snake.moveWithApple();
        snake.move();

        System.out.println(snake);

        snake.move(Direction.UP);
        snake.move();
        snake.moveWithApple();
        snake.move();
        snake.moveWithApple();
        snake.move();

        System.out.println(snake);
    }
}
