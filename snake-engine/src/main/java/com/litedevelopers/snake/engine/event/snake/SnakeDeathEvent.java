package com.litedevelopers.snake.engine.event.snake;

import com.litedevelopers.snake.engine.player.Player;
import com.litedevelopers.snake.engine.snake.Snake;

public class SnakeDeathEvent extends SnakeEvent {

    private final Player player;

    public SnakeDeathEvent(Snake snake, Player player) {
        super(snake);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
