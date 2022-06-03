package com.litedevelopers.snake.engine.event.fruit;

import com.litedevelopers.snake.engine.event.Event;
import com.litedevelopers.snake.engine.fruits.Fruit;

abstract class FruitEvent implements Event {

    private final Fruit fruit;

    protected FruitEvent(Fruit fruit) {
        this.fruit = fruit;
    }

    public Fruit getFruit() {
        return fruit;
    }

}
