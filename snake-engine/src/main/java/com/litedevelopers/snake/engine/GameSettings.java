package com.litedevelopers.snake.engine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Builder
@Accessors(fluent = true)
@AllArgsConstructor
public class GameSettings {

    private final double mapWidth;
    private final double mapHeight;
    private final float cameraWidth;
    private final float cameraHeight;
    private final double headSize;
    private final double speed;

    public GameSettings() {
        this.mapWidth = 800;
        this.mapHeight = 480;
        this.cameraHeight = 800;
        this.cameraWidth = 480;
        this.headSize = 20;
        this.speed = 5.0;
    }

}
