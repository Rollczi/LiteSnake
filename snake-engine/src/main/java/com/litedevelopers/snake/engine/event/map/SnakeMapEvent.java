package com.litedevelopers.snake.engine.event.map;

import com.litedevelopers.snake.engine.event.Event;
import com.litedevelopers.snake.engine.snake.SnakeMap;

abstract class SnakeMapEvent implements Event {

    private final SnakeMap snakeMap;

    SnakeMapEvent(SnakeMap snakeMap) {
        this.snakeMap = snakeMap;
    }

    public SnakeMap getSnakeMap() {
        return snakeMap;
    }

}
