package com.litedevelopers.snake.engine.snake.event.map;

import com.litedevelopers.snake.engine.snake.SnakeMap;
import com.litedevelopers.snake.engine.snake.event.Event;

public class SnakeCreateMapEvent extends SnakeMapEvent implements Event {

    public SnakeCreateMapEvent(SnakeMap snakeMap) {
        super(snakeMap);
    }

}
