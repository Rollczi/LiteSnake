package com.litedevelopers.snake.engine.ai;

import com.litedevelopers.snake.engine.math.MathUtils;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.player.PlayerInteraction;
import com.litedevelopers.snake.engine.snake.Snake;
import com.litedevelopers.snake.engine.snake.SnakeMap;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class AIInteraction implements PlayerInteraction {

    private final Random random = new Random();

    private Instant targetExpire = Instant.MIN;
    private Position target = new Position(0, 0);

    @Override
    public Position getDirection(SnakeMap snakeMap, Snake snake) {
        Position headPosition = snake.getHeadPosition();
        Position wallPoint = snakeMap.nearestPoint(headPosition);

        if (wallPoint.distance(headPosition) < snakeMap.height() / 10 + snakeMap.width() / 10) {
            return snakeMap.center();
        }

        if (targetExpire.isAfter(Instant.now())) {
            return target;
        }

        if (false && random.nextDouble() < 0.001) { // disabled
            for (Snake other : snakeMap.getSnakes()) {
                if (other.equals(snake)) {
                    continue;
                }

                target = other.getHeadPosition();
                targetExpire = Instant.now().plus(2, ChronoUnit.SECONDS);
                return target;
            }
        }

        Position direction = snake.getHeadPosition();
        double v = snake.getPartSize() * 36;

        for (Snake other : snakeMap.getSnakes(rotatedBox -> rotatedBox.center().distance(snake.getHeadPosition()) < v)) {
            double distance = snake.getHeadPosition().distance(other.getHeadPosition());

            direction = direction.add(headPosition.subtract(other.getHeadPosition()).multiple(distance * 0.003));
        }

        return direction;
    }

    private int boost = 0;

    @Override
    public boolean isBoosting() {
        if (random.nextDouble() < 0.01) {
            boost = 70;
        }

        if (boost <= 0) {
            return false;
        }

        boost--;
        return true;
    }

    @Override
    public void updateCamera(Position position) {
    }

}
