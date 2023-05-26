package com.litedevelopers.snake.libgdx;

import com.litedevelopers.snake.engine.GameSettings;
import com.litedevelopers.snake.engine.SnakeGameEngine;
import com.litedevelopers.snake.engine.ai.AIPlayer;
import com.litedevelopers.snake.engine.graphics.GraphicsController;
import com.litedevelopers.snake.engine.graphics.GraphicsRenderer;
import com.litedevelopers.snake.engine.player.Player;
import com.litedevelopers.snake.engine.event.EventHandler;
import com.litedevelopers.snake.libgdx.render.LibgdxElement;
import com.litedevelopers.snake.libgdx.render.LibgdxGraphicsApplication;
import com.litedevelopers.snake.libgdx.render.LibgdxRenderer;

public class SnakeGameLibgdx {

    private final SnakeGameEngine snakeGameEngine;

    private SnakeGameLibgdx(SnakeGameEngine snakeGameEngine) {
        this.snakeGameEngine = snakeGameEngine;
    }

    public SnakeGameEngine getSnakeGameEngine() {
        return snakeGameEngine;
    }

    public static SnakeGameLibgdx create(GameSettings settings, LibGDXLauncher launcher) {
        LibgdxPlayerInteraction state = new LibgdxPlayerInteraction();
        LibgdxGraphicsApplication application = new LibgdxGraphicsApplication(settings, state);
        GraphicsRenderer<LibgdxElement> renderer = new LibgdxRenderer(application);
        GraphicsController<LibgdxElement> controller = new GraphicsController<>(renderer);

        EventHandler eventHandler = new EventHandler();
        eventHandler.registerListener(controller);

        SnakeGameEngine snakeGameEngine = new SnakeGameEngine(eventHandler, settings);
        snakeGameEngine.registerPlayer(new Player("rollczi", state));

        for (int i = 0; i < 35; i++) {
            snakeGameEngine.registerPlayer(new AIPlayer("test" + i));
        }

        snakeGameEngine.startTick();

        launcher.launch(application);

        return new SnakeGameLibgdx(snakeGameEngine);
    }

}
