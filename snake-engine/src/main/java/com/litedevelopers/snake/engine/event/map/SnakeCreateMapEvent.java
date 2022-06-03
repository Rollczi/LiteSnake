package com.litedevelopers.snake.engine.event.map;

import com.litedevelopers.snake.engine.event.Event;
import com.litedevelopers.snake.engine.snake.SnakeMap;

public class SnakeCreateMapEvent extends SnakeMapEvent implements Event {

    public SnakeCreateMapEvent(SnakeMap snakeMap) {
        super(snakeMap);
    }

}
