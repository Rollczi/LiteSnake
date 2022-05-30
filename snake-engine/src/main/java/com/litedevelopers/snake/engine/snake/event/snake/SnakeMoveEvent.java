package com.litedevelopers.snake.engine.snake.event.snake;

import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.Snake;

public class SnakeMoveEvent extends SnakeEvent {

    private final Position from;
    private final Position to;

    public SnakeMoveEvent(Snake snake, Position from, Position to) {
        super(snake);
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

}
