package game.level;

import game.gameObjects.ALocatable;
import game.physics.AABoundingRect;
import game.physics.BoundingShape;

public abstract class LevelObject {
    //protected float x;
    //protected float y;
    protected BoundingShape boundingShape;
    public String type;

    protected float    x_velocity = 0;
    protected float    y_velocity = 0;

    protected float    direction  = 0;

    public LevelObject(float newX, float newY, float newWidth, float newHeight) {
        //this.x = x;
        //this.y = y;
        //create the bounding rect for the object
        boundingShape = new AABoundingRect(newX, newY, newWidth, newHeight);


    }
    public float getYVelocity() {
        return y_velocity;
    }

    public void setYVelocity(float f){
        y_velocity = f;
    }

    public float getXVelocity(){
        return x_velocity;
    }

    public void setXVelocity(float f){
        x_velocity = f;
    }

    public float getX(){
        return boundingShape.getX();
    }

    public float getY(){
        return boundingShape.getY();
    }

    public void setX(float f){
        boundingShape.setX(f);
        //updateBoundingShape();
    }

    public void setY(float f){
        boundingShape.setY(f);
    }
}
