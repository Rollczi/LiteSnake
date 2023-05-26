package com.litedevelopers.snake.engine;

import com.litedevelopers.snake.engine.fruits.FruitManager;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.player.Player;
import com.litedevelopers.snake.engine.player.PlayerInteraction;
import com.litedevelopers.snake.engine.player.PlayerCameraController;
import com.litedevelopers.snake.engine.snake.Snake;
import com.litedevelopers.snake.engine.snake.SnakeMap;
import com.litedevelopers.snake.engine.event.EventHandler;
import com.litedevelopers.snake.engine.event.map.SnakeCreateMapEvent;
import com.litedevelopers.snake.engine.event.map.SnakeDeleteMapEvent;
import com.litedevelopers.snake.engine.event.snake.SnakeDeathEvent;
import com.litedevelopers.snake.engine.event.snake.SnakeMoveEvent;
import com.litedevelopers.snake.engine.event.snake.SnakeSpawnEvent;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SnakeGameEngine implements AutoCloseable{

    private static final long TICK = 10L;

    private final Random random = new Random();
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final EventHandler eventHandler;
    private final FruitManager fruitManager;

    private final Set<Player> players = ConcurrentHashMap.newKeySet();
    private final Map<Snake, Player> snakeRelations = new ConcurrentHashMap<>();

    private GameSettings gameSettings;
    private SnakeMap snakeMap = SnakeMap.CLOSED;

    public SnakeGameEngine(EventHandler eventHandler) {
        this(eventHandler, new GameSettings());
    }

    public SnakeGameEngine(EventHandler eventHandler, GameSettings settings) {
        this.eventHandler = eventHandler;
        this.fruitManager = new FruitManager(eventHandler, settings);
        this.gameSettings = settings;
        this.eventHandler.registerListener(new PlayerCameraController());
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

    public void unregisterPlayer(Player player) {
        this.players.remove(player);
    }

    private boolean stated = false;

    public void startTick() {
        if (stated) {
            throw new IllegalStateException();
        }

        this.stated = true;
        this.schedule();
    }

    private Instant last = Instant.now();

    private void schedule() {
        executorService.schedule(() -> {
            try {
                this.tick();
            }
            catch (Throwable throwable) {
                throwable.printStackTrace();
            }

            this.schedule();
//            Duration between = Duration.between(last, Instant.now());
//            System.out.println(between.toMillis());
            last = Instant.now();
        }, TICK, TimeUnit.MILLISECONDS);
    }

    private void tick() {
        if (this.snakeMap.isClosed() || snakeMap.isEmpty()) {
            startGame();
        }

        this.fruitManager.spawnFruit();
        this.moveTick();
    }

    public void startGame() {
        this.snakeMap = new SnakeMap(Position.ZERO, new Position(this.gameSettings.mapWidth(), this.gameSettings.mapHeight()));
        this.eventHandler.call(new SnakeCreateMapEvent(this.snakeMap));
        this.fruitManager.setReach(this.snakeMap);

        for (Player player : this.players) {
            Snake snake = this.snakeMap.spawnSnake(player.getName(), gameSettings.headSize(), gameSettings.startLength());

            this.snakeRelations.put(snake, player);
            this.eventHandler.call(new SnakeSpawnEvent(snake));
        }
    }

    private void moveTick() {
        for (Snake snake : new HashSet<>(this.snakeMap.getSnakes())) {
            Player player = this.snakeRelations.get(snake);

            if (player == null) {
                continue;
            }

            if (!players.contains(player)) {
                this.snakeMap.killSnake(snake.getName());
                this.snakeRelations.remove(snake);
                this.eventHandler.call(new SnakeDeathEvent(snake, player));
                continue;
            }

            PlayerInteraction interaction = player.interaction();
            Position direction = EngineUtils.correct(snake, interaction.getDirection(snakeMap, snake), gameSettings.weightRotationBlocker());

            double speed = gameSettings.speed();

            if (interaction.isBoosting() && snake.getBoost() > 0.0D) {
                double boost = Math.min(snake.getBoost(), 2.0F);

                speed += boost;
                snake.setBoost(snake.getBoost() - boost);
            }

            Position from = snake.getHeadPosition();
            Position to = snake.move(speed, direction);

            this.eventHandler.call(new SnakeMoveEvent(snake, player, from, to));

            if (!this.snakeMap.containsAll(snake.getHead()) || this.snakeMap.isOnSnake(snake)) {
                this.snakeMap.killSnake(snake.getName());
                this.eventHandler.call(new SnakeDeathEvent(snake, player));

                if (snakeMap.getSnakes().isEmpty()) {
                    snakeMap.closeMap();
                    break;
                }
            }

            this.fruitManager.getFruit(snake.getHead()).ifPresent(value -> this.fruitManager.eatFruit(value, snake));
        }
    }

    @Override
    public void close() {
        this.executorService.shutdown();
    }

    public void closeMap() {
        snakeMap.closeMap();
        this.eventHandler.call(new SnakeDeleteMapEvent(this.snakeMap));
    }

    public void shutdownNow() {
        this.executorService.shutdownNow();
    }

}
