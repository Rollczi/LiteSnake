package com.litedevelopers.snake.engine.snake.event.snake;

import com.litedevelopers.snake.engine.snake.Snake;

public class SnakeDeathEvent extends SnakeEvent {

    public SnakeDeathEvent(Snake snake) {
        super(snake);
    }

}
