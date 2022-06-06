package com.litedevelopers.snake.engine.fruits;

import com.litedevelopers.snake.engine.entity.Entity;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.Snake;

public class Coconut extends AbstractFruit implements Entity {

    public Coconut(Position position, double size) {
        super(FruitType.COCONUT, position, size);
    }

    @Override
    public void applyOnSnake(Snake snake) {
        snake.addBoost(50.0);
    }

}
