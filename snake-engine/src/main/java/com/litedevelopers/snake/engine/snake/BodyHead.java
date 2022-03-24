package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;

public class BodyHead extends BodyPart{
    public BodyHead(Position min, Position max) {
        super(min, max);
    }

    @Override
    public BodyHead move(Position position) {
        Position difference = this.min.subtract(this.max);

        return new BodyHead(position, position.add(difference));
    }

}
