package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;

public class Apple {

    private final Position position;
    private final double size;

    public Apple(Position position, double size) {
        this.position = position;
        this.size = size;
    }

    public BoundingBox getBoundingBox() {
        return new BoundingBox(this.position.subtract(size), this.position.add(size));
    }

}
