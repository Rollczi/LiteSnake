package com.litedevelopers.snake.engine;

import com.litedevelopers.snake.engine.event.fruit.FruitEatEvent;
import com.litedevelopers.snake.engine.fruits.Fruit;
import com.litedevelopers.snake.engine.fruits.FruitManager;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.platform.Player;
import com.litedevelopers.snake.engine.platform.PlayerInteractionController;
import com.litedevelopers.snake.engine.snake.Snake;
import com.litedevelopers.snake.engine.snake.SnakeMap;
import com.litedevelopers.snake.engine.event.EventHandler;
import com.litedevelopers.snake.engine.event.map.SnakeCreateMapEvent;
import com.litedevelopers.snake.engine.event.map.SnakeDeleteMapEvent;
import com.litedevelopers.snake.engine.event.snake.SnakeDeathEvent;
import com.litedevelopers.snake.engine.event.snake.SnakeMoveEvent;
import com.litedevelopers.snake.engine.event.snake.SnakeSpawnEvent;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SnakeGameEngine implements AutoCloseable{

    private static final long TICK = 10L;

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final EventHandler eventHandler;
    private final FruitManager fruitManager;

    private final Set<Player> players = new HashSet<>();
    private final Map<Snake, Player> snakeRelations = new HashMap<>();

    private GameSettings gameSettings;
    private SnakeMap snakeMap = SnakeMap.CLOSED;

    public SnakeGameEngine(EventHandler eventHandler) {
        this(eventHandler, new GameSettings());
    }

    public SnakeGameEngine(EventHandler eventHandler, GameSettings settings) {
        this.eventHandler = eventHandler;
        this.fruitManager = new FruitManager(eventHandler, settings);
        this.gameSettings = settings;
        this.eventHandler.registerListener(new PlayerInteractionController());
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
            this.snakeMap = new SnakeMap(Position.ZERO, new Position(this.gameSettings.mapWidth(), this.gameSettings.mapHeight()));
            this.eventHandler.call(new SnakeCreateMapEvent(this.snakeMap));
            this.fruitManager.setReach(this.snakeMap);

            for (Player player : this.players) {
                Snake snake = this.snakeMap.spawnSnake(player.getName(), gameSettings.headSize());

                this.snakeRelations.put(snake, player);
                this.eventHandler.call(new SnakeSpawnEvent(snake));
            }
        }


        //System.out.println(this.fruitManager.getFruits().size());
        this.fruitManager.spawnFruit();
        this.moveTick();
    }

    private void moveTick() {
        for (Snake snake : new HashSet<>(this.snakeMap.getSnakes())) {
            Player player = this.snakeRelations.get(snake);

            if (player == null) {
                continue;
            }

            Position direction = player.getDirection(snake);

            Position from = snake.getPosition();
            Position to = snake.move(gameSettings.speed(), direction);

            this.eventHandler.call(new SnakeMoveEvent(snake, player, from, to));

            if (!this.snakeMap.containsAll(snake.getHead())) {
                this.snakeMap.killSnake(snake.getName());
                this.eventHandler.call(new SnakeDeathEvent(snake));

                if (snakeMap.getSnakes().isEmpty()) {
                    snakeMap.closeMap();
                    break;
                }
            }

            this.fruitManager.getFruit(snake.getHead()).ifPresent(value -> {
                value.applyOnSnake(snake);
                eventHandler.call(new FruitEatEvent(value, snake));
            });
        }
    }

    @Override
    public void close() {
        this.executorService.shutdown();
    }

    public void shutdownNow() {
        this.executorService.shutdownNow();
    }

}
