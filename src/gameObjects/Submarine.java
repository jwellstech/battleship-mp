package gameObjects;

import java.awt.*;

public class Submarine extends ALocatable {
    private double velocity;
    private double direction;

    public Submarine(double initX, double initY) {
        super(initX, initY);
    }

    public void move(int deltaX, int deltaY) {
        setX(getX() + deltaX);
        setY(getY() + deltaY);
    }
    private double getXVelocity() {
        return velocity*Math.cos(direction);
    }
}
