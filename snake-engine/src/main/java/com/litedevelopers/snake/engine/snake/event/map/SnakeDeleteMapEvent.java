package com.litedevelopers.snake.engine.snake.event.map;

import com.litedevelopers.snake.engine.snake.SnakeMap;
import com.litedevelopers.snake.engine.snake.event.Event;

public class SnakeDeleteMapEvent extends SnakeMapEvent implements Event {

    public SnakeDeleteMapEvent(SnakeMap snakeMap) {
        super(snakeMap);
    }

}
