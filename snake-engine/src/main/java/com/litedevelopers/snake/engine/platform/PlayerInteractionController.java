package com.litedevelopers.snake.engine.platform;

import com.litedevelopers.snake.engine.event.Listener;
import com.litedevelopers.snake.engine.event.Subscribe;
import com.litedevelopers.snake.engine.event.snake.SnakeMoveEvent;

import java.time.Duration;
import java.time.Instant;

public class PlayerInteractionController implements Listener {

    private final static Duration NEXT = Duration.ofMillis(400);
    private Instant lastUpdate = Instant.MIN;

    @Subscribe
    private void onMove(SnakeMoveEvent event) {
        if (Instant.now().isBefore(lastUpdate.plus(NEXT))) {
            return;
        }

        event.getPlayer().setCamera(event.getSnake().getPosition());
        lastUpdate = Instant.now();
    }

}
