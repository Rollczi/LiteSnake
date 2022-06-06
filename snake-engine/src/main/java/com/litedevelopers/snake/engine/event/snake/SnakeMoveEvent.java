package com.litedevelopers.snake.engine.event.snake;

import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.player.Player;
import com.litedevelopers.snake.engine.snake.Snake;

public class SnakeMoveEvent extends SnakeEvent {

    private final Player player;
    private final Position from;
    private final Position to;

    public SnakeMoveEvent(Snake snake, Player player, Position from, Position to) {
        super(snake);
        this.player = player;
        this.from = from;
        this.to = to;
    }

    public Player getPlayer() {
        return player;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

}
