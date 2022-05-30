package com.litedevelopers.snake.engine.snake.event.snake;

import com.litedevelopers.snake.engine.snake.Snake;
import com.litedevelopers.snake.engine.snake.event.Event;

abstract class SnakeEvent implements Event {

    private final Snake snake;

    SnakeEvent(Snake snake) {
        this.snake = snake;
    }

    public Snake getSnake() {
        return snake;
    }

}
