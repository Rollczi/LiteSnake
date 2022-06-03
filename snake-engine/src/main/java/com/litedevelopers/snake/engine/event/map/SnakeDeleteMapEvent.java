package com.litedevelopers.snake.engine.event.map;

import com.litedevelopers.snake.engine.event.Event;
import com.litedevelopers.snake.engine.snake.SnakeMap;

public class SnakeDeleteMapEvent extends SnakeMapEvent implements Event {

    public SnakeDeleteMapEvent(SnakeMap snakeMap) {
        super(snakeMap);
    }

}
