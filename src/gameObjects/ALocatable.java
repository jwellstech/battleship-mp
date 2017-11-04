package gameObjects;

import java.awt.*;

public class ALocatable implements Locatable {
    private double x, y;

    public ALocatable(double initX, double initY) {
        x = initX;
        y = initY;
    }

    public double getX() {
        return x;
    }

    public void setX(double newVal) {
        x = newVal;
    }

    public double getY() {
        return y;
    }

    public void setY(double newVal) {
        y = newVal;
    }
}
