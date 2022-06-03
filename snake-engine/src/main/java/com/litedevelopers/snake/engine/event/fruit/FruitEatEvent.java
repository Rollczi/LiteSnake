package com.litedevelopers.snake.engine.event.fruit;

import com.litedevelopers.snake.engine.fruits.Fruit;
import com.litedevelopers.snake.engine.snake.Snake;

public class FruitEatEvent extends FruitEvent {

    private final Snake by;

    protected FruitEatEvent(Fruit fruit, Snake by) {
        super(fruit);
        this.by = by;
    }

    public Snake getBy() {
        return by;
    }

}
