package com.litedevelopers.snake.minecraft;

import com.litedevelopers.snake.engine.GameSettings;
import com.litedevelopers.snake.engine.SnakeGameEngine;
import com.litedevelopers.snake.engine.event.EventHandler;
import com.litedevelopers.snake.engine.graphics.GraphicsController;
import com.litedevelopers.snake.engine.graphics.GraphicsElement;
import com.litedevelopers.snake.engine.graphics.GraphicsRenderer;
import org.bukkit.plugin.java.JavaPlugin;

public class SnakePlugin extends JavaPlugin {

    private EventHandler eventHandler;
    private SnakeGameEngine gameEngine;

    @Override
    public void onEnable() {
        NmsEntityRenderer renderer = new NmsEntityRenderer();
        GraphicsController<NmsEntity> graphicsController = new GraphicsController<>(renderer);

        GameSettings settings = GameSettings.builder()
                .mapWidth(10F)
                .mapHeight(10F)

                .headSize(0.5)
                .startLength(5)
                .fruitSize(0.5)
                .speed(1.65)
                .fortune(0.1)
                .build();

        this.eventHandler = new EventHandler();
        this.eventHandler.registerListener(graphicsController);
        this.gameEngine = new SnakeGameEngine(eventHandler, settings);
    }

    @Override
    public void onDisable() {
        this.gameEngine.shutdownNow();
    }

}
