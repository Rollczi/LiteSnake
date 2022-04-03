package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.Position;

public class BodyHead extends BodyPart{
    public BodyHead(Position min, Position max) {
        super(min, max);
    }

    public BodyHead(Position center, double size) {
        super(center, size);
    }
}
