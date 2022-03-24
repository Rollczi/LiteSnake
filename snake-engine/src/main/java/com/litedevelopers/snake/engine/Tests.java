package com.litedevelopers.snake.engine;

import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.SnakeGrid;

public class Tests {

    public static void main(String[] args) {
        SnakeGrid snake = new SnakeGrid("Kamil Ślimak", 1);

        snake.moveWithApple(new Position(1.5,0.3));
        snake.move(new Position(1.5, 1.4));
        snake.moveWithApple(new Position(2.5, 1.6));
        snake.move(new Position(3.3, 1.6));

        System.out.println(snake);
    }
}
