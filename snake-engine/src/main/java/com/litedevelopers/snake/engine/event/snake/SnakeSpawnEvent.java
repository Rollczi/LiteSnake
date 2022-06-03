package com.litedevelopers.snake.engine.event.snake;

import com.litedevelopers.snake.engine.snake.Snake;

public class SnakeSpawnEvent extends SnakeEvent {

    public SnakeSpawnEvent(Snake snake) {
        super(snake);
    }

}
