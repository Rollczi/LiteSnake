package com.litedevelopers.snake.engine.fruits;

import com.litedevelopers.snake.engine.entity.Entity;
import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;

public abstract class AbstractFruit implements Fruit, Entity {

    protected final FruitType type;
    protected final Position position;
    protected final double size;

    AbstractFruit(FruitType type, Position position, double size) {
        this.type = type;
        this.position = position;
        this.size = size;
    }

    @Override
    public FruitType getType() {
        return type;
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
