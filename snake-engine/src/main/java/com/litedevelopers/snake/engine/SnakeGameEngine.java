package com.litedevelopers.snake.engine;

import com.litedevelopers.snake.engine.math.Direction;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.platform.Player;
import com.litedevelopers.snake.engine.snake.BodyHead;
import com.litedevelopers.snake.engine.snake.Snake;
import com.litedevelopers.snake.engine.snake.SnakeMap;
import com.litedevelopers.snake.engine.snake.event.EventHandler;
import com.litedevelopers.snake.engine.snake.event.map.SnakeCreateMapEvent;
import com.litedevelopers.snake.engine.snake.event.map.SnakeDeleteMapEvent;
import com.litedevelopers.snake.engine.snake.event.snake.SnakeMoveEvent;
import com.litedevelopers.snake.engine.snake.event.snake.SnakeSpawnEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SnakeGameEngine implements AutoCloseable{

    private static final long TICK = 10L;

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final EventHandler eventHandler;

    private final Set<Player> players = new HashSet<>();
    private final Map<Snake, Player> snakeRelations = new HashMap<>();

    private GameSettings gameSettings = new GameSettings();
    private SnakeMap snakeMap = SnakeMap.CLOSED;

    public SnakeGameEngine(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
        this.schedule();
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    public void registerPlayer(Player player) {
        this.players.add(player);
    }

    private void schedule() {
        executorService.schedule(() -> {
            try {
                this.tick();
            }
            catch (Throwable throwable) {
                throwable.printStackTrace();
            }

            this.schedule();
        }, TICK, TimeUnit.MILLISECONDS);
    }

    private void tick() {
        if (this.snakeMap.isClosed() || snakeMap.isEmpty()) {
            this.eventHandler.call(new SnakeDeleteMapEvent(this.snakeMap));
            this.snakeMap = new SnakeMap(Position.ZERO, new Position(this.gameSettings.getMapWidth(), this.gameSettings.getMapHeight()));
            this.eventHandler.call(new SnakeCreateMapEvent(this.snakeMap));

            for (Player player : this.players) {
                Snake snake = this.snakeMap.spawnSnake(player.getName(), gameSettings.headSize());

                this.snakeRelations.put(snake, player);
                this.eventHandler.call(new SnakeSpawnEvent(snake));
            }
        }

        this.moveTick();
    }

    private void moveTick() {
        for (Snake snake : new HashSet<>(this.snakeMap.getSnakes())) {
            Player player = this.snakeRelations.get(snake);

            if (player == null) {
                continue;
            }

            Direction direction = player.getDirection(snake);

            Position from = snake.getPosition();
            Position to = snake.move(5.0, direction);

            this.eventHandler.call(new SnakeMoveEvent(snake, from, to));

            if (!this.snakeMap.contains(snake.getPosition())) {
                snakeMap.killSnake(snake.getName());

                if (snakeMap.getSnakes().isEmpty()) {
                    snakeMap.closeMap();
                    break;
                }
            }
        }
    }

    @Override
    public void close() {
        this.executorService.shutdownNow();
    }

}
