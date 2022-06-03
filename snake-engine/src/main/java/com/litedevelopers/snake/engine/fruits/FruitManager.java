package com.litedevelopers.snake.engine.fruits;

import com.litedevelopers.snake.engine.event.EventHandler;

import java.util.HashSet;
import java.util.Set;

public class FruitManager {

    private final EventHandler eventHandler;
    private final Set<Fruit> fruits = new HashSet<>();

    public FruitManager(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public Set<Fruit> getFruits() {
        return fruits;
    }

}
