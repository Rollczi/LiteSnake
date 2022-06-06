package com.litedevelopers.snake.engine.player;

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

    public PlayerInteraction interaction() {
        return this.directionProvider;
    }

}
