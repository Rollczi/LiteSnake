package com.litedevelopers.snake.engine;

import com.litedevelopers.snake.engine.snake.event.Listener;
import com.litedevelopers.snake.engine.snake.event.Subscribe;
import com.litedevelopers.snake.engine.snake.event.snake.SnakeDeathEvent;
import com.litedevelopers.snake.engine.snake.event.snake.SnakeMoveEvent;

public class DebugListener implements Listener {

    @Subscribe
    public void onDebug(SnakeMoveEvent event) {
        System.out.println(event.getFrom() + " " + event.getTo());
    }

}
