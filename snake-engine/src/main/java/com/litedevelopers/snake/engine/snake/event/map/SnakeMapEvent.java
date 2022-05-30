package com.litedevelopers.snake.engine.snake.event.map;

import com.litedevelopers.snake.engine.snake.SnakeMap;
import com.litedevelopers.snake.engine.snake.event.Event;

abstract class SnakeMapEvent implements Event {

    private final SnakeMap snakeMap;

    SnakeMapEvent(SnakeMap snakeMap) {
        this.snakeMap = snakeMap;
    }

    public SnakeMap getSnakeMap() {
        return snakeMap;
    }

}
