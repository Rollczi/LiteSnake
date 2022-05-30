package com.litedevelopers.snake.engine;

public class GameSettings {

    private final double mapWidth;
    private final double mapHeight;
    private final double headSize;

    public GameSettings() {
        this.mapWidth = 150;
        this.mapHeight = 150;
        this.headSize = 10;
    }

    public GameSettings(double mapWidth, double mapHeight, double headSize) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.headSize = headSize;
    }

    public double getMapHeight() {
        return mapHeight;
    }

    public double getMapWidth() {
        return mapWidth;
    }

    public double headSize() {
        return headSize;
    }

}
