package com.litedevelopers.snake.minecraft.player;

import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.player.PlayerInteraction;
import com.litedevelopers.snake.engine.snake.Snake;
import com.litedevelopers.snake.engine.snake.SnakeMap;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.UUID;

class MinecraftPlayer extends com.litedevelopers.snake.engine.player.Player {

    private final UUID uuid;
    private final PlayerState state;

    public MinecraftPlayer(String name, UUID uuid, PlayerState state) {
        super(name, state);
        this.state = state;
        this.uuid = uuid;
    }

    public UUID getPlayerUuid() {
        return uuid;
    }

    public PlayerState getState() {
        return state;
    }

}
