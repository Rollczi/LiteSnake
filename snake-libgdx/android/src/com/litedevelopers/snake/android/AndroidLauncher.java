package com.litedevelopers.snake.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.litedevelopers.snake.engine.GameSettings;
import com.litedevelopers.snake.engine.SnakeGameEngine;
import com.litedevelopers.snake.libgdx.SnakeGameLibgdx;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GameSettings settings = GameSettings.builder()
				.mapWidth(1000F)
				.mapHeight(1000F)

				.cameraWidth(400F)
				.cameraHeight(240F)
				.weightRotationBlocker(0.25)

				.headSize(5)
				.startLength(5)
				.fruitSize(5)
				.speed(0.65)
				.fortune(0.1)
				.build();

		SnakeGameLibgdx.create(settings, application -> {
			AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

			initialize(application, config);
		});
	}
}
