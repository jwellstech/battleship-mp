package game.level;

import game.Settings;
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
    private        int   damage = 0;


    public LevelObject(float newX, float newY, float newWidth, float newHeight) {
        //create the bounding rect for the object
        boundingShape = new AABoundingRect(newX, newY, newWidth, newHeight);
    }
    public BoundingShape getBoundingShape() {
        return boundingShape;
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
        return (float)(getVelocity()*Math.sin(direction));
    }
    public float getXVelocity(){
        return (float)(getVelocity()*Math.cos(direction));
    }
    public float getDirection() {
        return direction;
    }
    public void setDirection(float f) {
        direction = f;
    }
    //rotate by some number of degrees
    public void rotate(float degrees) {
        setDirection((float)((getDirection() + (float)Math.toRadians(degrees)) % (2*Math.PI)));
    }
    public void doRotate(int leftOrRight) {
        switch (leftOrRight) {
            case (1):
                rotate(Settings.ROTATION_SPEED);
                break;
            case (-1):
                rotate(-1*Settings.ROTATION_SPEED);
                break;
        }
    }
    public int getDamage() {
        return damage;
    }
    public void move(long delta) {
        setX(getX() + (getXVelocity() /** delta*/));
        setY(getY() + (getYVelocity() /** delta*/));
    }
    public void update(long delta) {
        move(delta);
    }
    public boolean checkCollisionInRadius(AABoundingRect rect, float radius) {
        float loX = getX() + ((AABoundingRect)getBoundingShape()).getWidth()/2;
        float loY = getY() + ((AABoundingRect)getBoundingShape()).getHeight()/2;
        if(Math.hypot(rect.getX() - loX, rect.getY() - loY ) <= radius) {
            return true;
        }
        if(Math.hypot(rect.getX() + rect.getWidth() - loX, rect.getY() - loY ) <= radius) {
            return true;
        }
        if(Math.hypot(rect.getX() - loX, rect.getY() + rect.getHeight() - loY ) <= radius) {
            return true;
        }
        if(Math.hypot(rect.getX() + rect.getWidth() - loX, rect.getY() + rect.getHeight() - loY ) <= radius) {
            return true;
        }
        return false;
    }
}
