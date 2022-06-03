package com.litedevelopers.snake.engine.math;

public class RotatedBox extends BoundingBox {

    private final Position direction;

    public RotatedBox(Position min, Position max, Position direction) {
        super(min, max);
        this.direction = direction;
    }

    public Position direction() {
        return direction;
    }

    public float rotation() {
        return (float) Math.atan2(direction.y, direction.x);// * MathUtils.radiansToDegrees;;
    }

    public RotatedBox direction(Position direction) {
        return new RotatedBox(this.min, this.max, direction);
    }

    public RotatedBox move(Position position) {
        Position min = this.min.add(position);
        Position max = this.max.add(position);

        return new RotatedBox(min, max, direction);
    }


}
