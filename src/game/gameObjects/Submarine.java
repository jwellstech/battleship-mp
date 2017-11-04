package game.gameObjects;

public class Submarine extends ALocatable {
    private double velocity;
    private double direction;

    public Submarine(double initX, double initY) {
        super(initX, initY);
    }

    public void move() {
        setX(getX() + getXVelocity());
        setY(getY() + getYVelocity());
    }
    public double getXVelocity() {
        return velocity*Math.cos(direction);
    }
    public double getYVelocity() {
        return velocity*Math.sin(direction);
    }

}
