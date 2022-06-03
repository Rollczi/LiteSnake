package com.litedevelopers.snake.engine.fruits;

import com.litedevelopers.snake.engine.entity.Entity;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.Snake;

public class Apple extends AbstractFruit implements Fruit, Entity {

    Apple(Position position, double size) {
        super(FruitType.APPLE, position, size);
    }

    @Override
    public void applyOnSnake(Snake snake) {
        snake.setLength(snake.getLength() + 1);
    }

}
