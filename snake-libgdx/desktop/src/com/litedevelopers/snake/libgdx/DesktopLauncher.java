package com.litedevelopers.snake.libgdx;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.litedevelopers.snake.engine.GameSettings;
import com.litedevelopers.snake.engine.SnakeGameEngine;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] arg) {
		GameSettings settings = GameSettings.builder()
				.mapWidth(1600F)
				.mapHeight(960F)

				.cameraWidth(1600F)
				.cameraHeight(960F)

				.headSize(30)
				.speed(1.25)
				.build();

		SnakeGameLibgdx snake = SnakeGameLibgdx.create(settings, application -> {
			Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

			config.setTitle("LiteSnake");
			config.setForegroundFPS(60);
			config.setWindowedMode(1600, 960);
			config.useVsync(true);

			new Lwjgl3Application(application, config);
		});

		SnakeGameEngine gameEngine = snake.getSnakeGameEngine();

		gameEngine.close();
	}
}
