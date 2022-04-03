package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;

public class BodyPart extends BoundingBox {

    private final Position center;

    public BodyPart(Position min, Position max) {
        super(min, max);
        this.center = new Position((min.getX() + max.getX()) / 2, (min.getY() + max.getY()) / 2);
    }

    public BodyPart(Position center, double size) {
        super(
                new Position(center.getX() - size / 2, center.getY() - size / 2),
                new Position(center.getX() + size / 2, center.getY() + size / 2));

        this.center = center;
    }

    public Position getCenter() {
        return center;
    }
}
