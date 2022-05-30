package com.litedevelopers.snake.engine;

public class GameSettings {

    private final double mapWith;
    private final double mapHeight;

    public GameSettings() {
        this.mapWith = 10;
        this.mapHeight = 10;
    }

    public GameSettings(double mapWith, double mapHeight) {
        this.mapWith = mapWith;
        this.mapHeight = mapHeight;
    }

    public double getMapHeight() {
        return mapHeight;
    }

    public double getMapWith() {
        return mapWith;
    }

}
