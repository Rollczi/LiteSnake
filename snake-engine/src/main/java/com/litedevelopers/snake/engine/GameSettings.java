package com.litedevelopers.snake.engine;

public class GameSettings {

    private double mapWidth = 800;
    private double mapHeight = 480;
    private float cameraWidth = 800;
    private float cameraHeight = 480;
    private double headSize  = 20;
    private int startLength  = 2;
    private double fruitSize  = 20;
    private double speed = 5.0;
    private double fortune = 0.1;
    private double weightRotationBlocker = 1.0;

    public GameSettings(double mapWidth, double mapHeight, float cameraWidth, float cameraHeight, double headSize, int startLength, double fruitSize, double speed, double fortune, double weightRotationBlocker) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.cameraWidth = cameraWidth;
        this.cameraHeight = cameraHeight;
        this.headSize = headSize;
        this.startLength = startLength;
        this.fruitSize = fruitSize;
        this.speed = speed;
        this.fortune = fortune;
        this.weightRotationBlocker = weightRotationBlocker;
    }

    public GameSettings() {
    }

    public static GameSettingsBuilder builder() {
        return new GameSettingsBuilder();
    }

    public double mapWidth() {
        return this.mapWidth;
    }

    public double mapHeight() {
        return this.mapHeight;
    }

    public float cameraWidth() {
        return this.cameraWidth;
    }

    public float cameraHeight() {
        return this.cameraHeight;
    }

    public double headSize() {
        return this.headSize;
    }

    public int startLength() {
        return this.startLength;
    }

    public double fruitSize() {
        return this.fruitSize;
    }

    public double speed() {
        return this.speed;
    }

    public double fortune() {
        return this.fortune;
    }

    public double weightRotationBlocker() {
        return this.weightRotationBlocker;
    }

    public static class GameSettingsBuilder {
        private double mapWidth;
        private double mapHeight;
        private float cameraWidth;
        private float cameraHeight;
        private double headSize;
        private int startLength;
        private double fruitSize;
        private double speed;
        private double fortune;
        private double weightRotationBlocker;

        GameSettingsBuilder() {
        }

        public GameSettingsBuilder mapWidth(double mapWidth) {
            this.mapWidth = mapWidth;
            return this;
        }

        public GameSettingsBuilder mapHeight(double mapHeight) {
            this.mapHeight = mapHeight;
            return this;
        }

        public GameSettingsBuilder cameraWidth(float cameraWidth) {
            this.cameraWidth = cameraWidth;
            return this;
        }

        public GameSettingsBuilder cameraHeight(float cameraHeight) {
            this.cameraHeight = cameraHeight;
            return this;
        }

        public GameSettingsBuilder headSize(double headSize) {
            this.headSize = headSize;
            return this;
        }

        public GameSettingsBuilder startLength(int startLength) {
            this.startLength = startLength;
            return this;
        }

        public GameSettingsBuilder fruitSize(double fruitSize) {
            this.fruitSize = fruitSize;
            return this;
        }

        public GameSettingsBuilder speed(double speed) {
            this.speed = speed;
            return this;
        }

        public GameSettingsBuilder fortune(double fortune) {
            this.fortune = fortune;
            return this;
        }

        public GameSettingsBuilder weightRotationBlocker(double weightRotationBlocker) {
            this.weightRotationBlocker = weightRotationBlocker;
            return this;
        }

        public GameSettings build() {
            return new GameSettings(mapWidth, mapHeight, cameraWidth, cameraHeight, headSize, startLength, fruitSize, speed, fortune, weightRotationBlocker);
        }

    }
}
