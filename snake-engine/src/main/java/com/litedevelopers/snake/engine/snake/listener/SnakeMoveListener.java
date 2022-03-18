package com.litedevelopers.snake.engine.snake.listener;

import com.litedevelopers.snake.engine.snake.Snake;

@FunctionalInterface
public interface SnakeMoveListener {

    void handle(Snake snake);

}