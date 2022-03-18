package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SnakeBody {

    private final List<SnakeBodyPart> snakeBodyParts;

    public SnakeBody(Collection<SnakeBodyPart> snakeBodyParts) {
        this.snakeBodyParts = new ArrayList<>(snakeBodyParts);
    }

    public boolean contains(Position position) {
        for (SnakeBodyPart snakeBodyPart : snakeBodyParts) {
            if (snakeBodyPart.contains(position)) {
                return true;
            }
        }

        return false;
    }

}
