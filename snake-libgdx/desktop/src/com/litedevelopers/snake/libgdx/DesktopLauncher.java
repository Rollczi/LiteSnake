package com.litedevelopers.snake.libgdx;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.litedevelopers.snake.engine.GameSettings;
import com.litedevelopers.snake.engine.SnakeGameEngine;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] arg) {
		GameSettings settings = GameSettings.builder()
				.mapWidth(3000F)
				.mapHeight(3000F)

				.cameraWidth(1600F)
				.cameraHeight(960F)

				.headSize(15)
				.startLength(5)
				.fruitSize(15)
				.speed(1.65)
				.fortune(0.1)
				.build();

		SnakeGameLibgdx snake = SnakeGameLibgdx.create(settings, application -> {
			Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

			config.setTitle("LiteSnake");
			config.setForegroundFPS(75);
			config.setWindowedMode(1600, 960);
			config.useVsync(true);

			new Lwjgl3Application(application, config);
		});

		SnakeGameEngine gameEngine = snake.getSnakeGameEngine();

		gameEngine.close();
	}
}
