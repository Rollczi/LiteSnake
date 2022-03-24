package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;

public class BodyPart extends BoundingBox {
    public BodyPart(Position min, Position max) {
        super(min, max);
    }
}
