package com.litedevelopers.snake.engine.test;

import com.litedevelopers.snake.engine.GameSettings;
import com.litedevelopers.snake.engine.SnakeGameEngine;
import com.litedevelopers.snake.engine.graphics.GraphicsController;
import com.litedevelopers.snake.engine.player.Player;
import com.litedevelopers.snake.engine.event.EventHandler;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

class TestEngine {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Test
    void test() {
        AndroidRenderer androidRenderer = new AndroidRenderer(); // klasa do renderowania
        GraphicsController<AndroidElement> graphicsController = new GraphicsController<>(androidRenderer); // klasa do kontrolli renderowania grafiki

        EventHandler eventHandler = new EventHandler(); // Obsługa zdarzeń
        eventHandler.registerListener(graphicsController); // Zarejestrowanie listener'a

        SnakeGameEngine snakeGameEngine = new SnakeGameEngine(eventHandler); // silnik (co 50 ms wykonuje tick od teraz)
        snakeGameEngine.setGameSettings(new GameSettings()); // ustawienia silnika

        Player player = new Player("test", new RandomPlayerInteraction()); // nowy gracz (w naszym przypadku 1 wystarczy)

        snakeGameEngine.registerPlayer(player); // zajejestrowanie gracza w silniku

        snakeGameEngine.close(); // wyłączenie gry
    }

}
