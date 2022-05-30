package com.litedevelopers.snake.graphics2d;

import com.litedevelopers.snake.engine.DebugListener;
import com.litedevelopers.snake.engine.GameSettings;
import com.litedevelopers.snake.engine.SnakeGameEngine;
import com.litedevelopers.snake.engine.graphics.GraphicsController;
import com.litedevelopers.snake.engine.platform.Player;
import com.litedevelopers.snake.engine.snake.event.EventHandler;

import java.awt.*;

class SinglePlayerSnakeGame {

    private SnakeGameEngine snakeGameEngine;

    void start() {
        JavaRenderer javaRenderer = JavaRenderer.create();
        GraphicsController<JavaElement2d> graphicsController = new GraphicsController<>(javaRenderer);
        EventHandler eventHandler = new EventHandler();

        eventHandler.registerListener(graphicsController);
        eventHandler.registerListener(new DebugListener());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.snakeGameEngine = new SnakeGameEngine(eventHandler);
        this.snakeGameEngine.setGameSettings(new GameSettings(screenSize.width, screenSize.height, 50));

        Player player = new Player("user", new JavaPlayerInteraction(javaRenderer.getWindow()));

        this.snakeGameEngine.registerPlayer(player);
    }

    void stop() {
        this.snakeGameEngine.close();
    }

}
