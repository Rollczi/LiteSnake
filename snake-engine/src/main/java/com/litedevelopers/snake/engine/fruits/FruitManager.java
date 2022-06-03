package com.litedevelopers.snake.engine.fruits;

import com.litedevelopers.snake.engine.GameSettings;
import com.litedevelopers.snake.engine.event.EventHandler;
import com.litedevelopers.snake.engine.event.fruit.FruitSpawnEvent;
import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
public class FruitManager {

    private final Random random = new Random();
    private final EventHandler eventHandler;

    private final Set<FruitCreator> fruitsCreators = new HashSet<>();
    private final Set<Fruit> fruits = new HashSet<>();
    private BoundingBox reach = new BoundingBox(Position.ZERO, Position.ONE);

    public FruitManager(EventHandler eventHandler, GameSettings settings) {
        this.eventHandler = eventHandler;
        this.registerCreator(position -> new Coconut(position, settings.fruitSize()));
        this.registerCreator(position -> new Apple(position, settings.fruitSize()));
    }

    public void setReach(BoundingBox reach) {
        this.reach = reach;
    }

    public void registerCreator(FruitCreator creator) {
        this.fruitsCreators.add(creator);
    }

    public Set<Fruit> getFruits() {
        return fruits;
    }

    public Optional<Fruit> spawnFruit() {
        if (!(random.nextDouble() <= 0.01)) {
            return Optional.empty();
        }

        int x = random.nextInt((int) reach.width());
        int y = random.nextInt((int) reach.height());

        Fruit fruit = fruitsCreators.stream()
                .skip(random.nextInt(fruitsCreators.size()))
                .findFirst()
                .orElseThrow(IllegalStateException::new)
                .create(reach.getMin().add(x, y));


        fruits.add(fruit);
        eventHandler.call(new FruitSpawnEvent(fruit));

        return Optional.of(fruit);
    }

}
