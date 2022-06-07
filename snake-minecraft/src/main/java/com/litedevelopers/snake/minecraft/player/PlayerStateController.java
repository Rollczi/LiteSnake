package com.litedevelopers.snake.minecraft.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlayerStateController implements Listener {

    private final Map<UUID, MinecraftPlayer> states = new HashMap<>();

    void addState(MinecraftPlayer minecraftPlayer) {
        states.put(minecraftPlayer.getPlayerUuid(), minecraftPlayer);
    }

    @EventHandler
    void onMove(PlayerMoveEvent event) {
        MinecraftPlayer minecraftPlayer = states.get(event.getPlayer().getUniqueId());

        if (minecraftPlayer == null) {
            return;
        }

        PlayerState state = minecraftPlayer.getState();

    }

}
