package com.litedevelopers.snake.engine;

import com.litedevelopers.snake.engine.math.MathUtils;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.Snake;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class EngineUtils {

    private final static Map<Double, Integer> ANGLE_BLOCKER = Map.of(
            40.0, 15_000,
            20.0, 7_000,
            10.0, 5_000,
            2.0, 3_000,
            0.0, 1_000
    );

    private final static List<Double> ANGLES = ANGLE_BLOCKER.keySet().stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

    static Position correct(Snake snake, Position position) {
        Position lastOk = snake.getDirection();
        Position direction = position.subtract(snake.getHead().center());

        if (direction.getLength() < snake.getPartSize() / 2) {
            return lastOk;
        }

        double angleOld = MathUtils.halfAngle(MathUtils.toAngle(lastOk));
        double angleNow = MathUtils.halfAngle(MathUtils.toAngle(direction));

        for (double angle : ANGLES) {
            if (Math.abs(angleNow - angleOld) < angle) {
                continue;
            }

            Position middle = direction
                    .add(lastOk.multiple(ANGLE_BLOCKER.get(angle)))
                    .normalize();

            direction = new Position(middle.getX(), middle.getY());
            break;
        }

        if (direction.isZero() || direction.isNaN()) {
            direction = new Position(0, 1);
        }

        return direction;
    }

}
