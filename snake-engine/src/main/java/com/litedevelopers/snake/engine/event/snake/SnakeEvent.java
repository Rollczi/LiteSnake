package com.litedevelopers.snake.engine.event.snake;

import com.litedevelopers.snake.engine.event.Event;
import com.litedevelopers.snake.engine.snake.Snake;

abstract class SnakeEvent implements Event {

    private final Snake snake;

    SnakeEvent(Snake snake) {
        this.snake = snake;
    }

    public Snake getSnake() {
        return snake;
    }

}
