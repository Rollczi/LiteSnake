package com.litedevelopers.snake.engine.fruits;

import com.litedevelopers.snake.engine.entity.Entity;
import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;

public abstract class AbstractFruit implements Entity {

    protected final Position position;
    protected final double size;

    AbstractFruit(Position position, double size) {
        this.position = position;
        this.size = size;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return new BoundingBox(this.position.subtract(size), this.position.add(size));
    }

}
