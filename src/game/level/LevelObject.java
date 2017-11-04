package game.level;

import game.physics.AABoundingRect;
import game.physics.BoundingShape;

public abstract class LevelObject {
    //protected float x;
    //protected float y;
    protected BoundingShape boundingShape;
    public    String        type;

    //starts stationary
    private float    velocity;
    //default orientation
    private float    direction  = (float)Math.PI/2;

    public LevelObject(float newX, float newY, float newWidth, float newHeight) {
        //create the bounding rect for the object
        boundingShape = new AABoundingRect(newX, newY, newWidth, newHeight);
    }
    public float getX(){
        return boundingShape.getX();
    }

    public float getY(){
        return boundingShape.getY();
    }

    public void setX(float f){
        boundingShape.setX(f);
    }

    public void setY(float f){
        boundingShape.setY(f);
    }
    public void setVelocity(float f) {
        velocity = f;
    }
    public float getVelocity() {
        return velocity;
    }
    public float getYVelocity() {
        return (float)(velocity*Math.sin(direction));
    }
    public float getXVelocity(){
        return (float)(velocity*Math.cos(direction));
    }
    public float getDirection() {
        return direction;
    }
    public void setDirection(float f) {
        direction = f;
    }
    //rotate by some number of degrees
    public void rotate(float degrees) {
        setDirection(getDirection() + (float)Math.toRadians(degrees));
    }
}
