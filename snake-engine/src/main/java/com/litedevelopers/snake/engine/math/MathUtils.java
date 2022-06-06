package com.litedevelopers.snake.engine.math;

import java.util.Random;

public final class MathUtils {

    private static final Random RANDOM = new Random();

    private MathUtils() {}

    public static double toAngle(Position position) {
        return (180.0 / Math.PI * Math.atan2(position.y, position.x));
    }

    public static double halfAngle(double angle) {
        return Math.abs(Math.min(angle, 360 - angle));
    }

    public static Position random(BoundingBox box) {
        int x = RANDOM.nextInt((int) box.width());
        int y = RANDOM.nextInt((int) box.height());

        return box.min.add(x, y);
    }

}
