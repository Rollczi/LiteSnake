package com.litedevelopers.snake.engine.player;

import com.litedevelopers.snake.engine.event.Listener;
import com.litedevelopers.snake.engine.event.Subscribe;
import com.litedevelopers.snake.engine.event.map.SnakeCreateMapEvent;
import com.litedevelopers.snake.engine.event.map.SnakeDeleteMapEvent;
import com.litedevelopers.snake.engine.event.snake.SnakeDeathEvent;
import com.litedevelopers.snake.engine.event.snake.SnakeMoveEvent;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.Snake;
import com.litedevelopers.snake.engine.snake.SnakeMap;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class PlayerCameraController implements Listener {

    private final static Duration NEXT = Duration.ofMillis(1);
    private final Map<String, Instant> lastUpdate = new HashMap<>();

    private final Map<Snake, Set<Player>> snakeViewers = new HashMap<>();
    private SnakeMap last = SnakeMap.CLOSED;

    @Subscribe
    private void onMove(SnakeMoveEvent event) {
        Player player = event.getPlayer();
        Instant instant = lastUpdate.getOrDefault(player.getName(), Instant.MIN);

        if (Instant.now().isBefore(instant)) {
            return;
        }

        Position camera = event.getSnake().getHeadPosition();

        player.interaction().updateCamera(camera);
        lastUpdate.put(player.getName(), Instant.now().plus(NEXT));

        Set<Player> viewers = snakeViewers.get(event.getSnake());

        if (viewers == null) {
            return;
        }

        for (Player viewer : viewers) {
            viewer.interaction().updateCamera(camera);
        }
    }


    @Subscribe
    private void onDeath(SnakeDeathEvent event) {
        Player player = event.getPlayer();
        Set<Snake> snakes = last.getSnakes();
        Optional<Snake> first = snakes.stream()
                .skip(1L)
                .findFirst();

        if (first.isEmpty()) {
            return;
        }

        Set<Player> viewers = this.snakeViewers.computeIfAbsent(first.get(), k -> new HashSet<>());

        viewers.add(player);
    }

    @Subscribe
    private void onCreate(SnakeCreateMapEvent event) {
        this.snakeViewers.clear();
        this.last = event.getSnakeMap();
    }

    @Subscribe
    private void onCreate(SnakeDeleteMapEvent event) {
        this.snakeViewers.clear();
    }

}
