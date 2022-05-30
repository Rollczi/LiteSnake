package com.litedevelopers.snake.engine.platform;

import com.litedevelopers.snake.engine.math.Direction;
import com.litedevelopers.snake.engine.snake.Snake;

import java.util.UUID;

public class Player {

    private final UUID uuid;
    private final String name;
    private final PlayerInteraction directionProvider;

    public Player(String name, PlayerInteraction directionProvider) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.directionProvider = directionProvider;
    }

    public Player(UUID uuid, String name, PlayerInteraction directionProvider) {
        this.uuid = uuid;
        this.name = name;
        this.directionProvider = directionProvider;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Direction getDirection(Snake snake) {
        return directionProvider.getDirection(snake);
    }

}
