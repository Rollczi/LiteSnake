package com.litedevelopers.snake.engine;

import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.SnakeGrid;

public class Tests {

    public static void main(String[] args) {
        SnakeGrid snake = new SnakeGrid("Kamil Ślimak", 1);

        snake.moveWithApple(new Position(1,0));
        snake.move(new Position(1, 1));
        snake.moveWithApple(new Position(2, 1));
        snake.move(new Position(3, 1));

        System.out.println(snake);
    }
}
