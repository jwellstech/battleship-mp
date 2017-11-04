package game.physics;

public abstract class BoundingShape {

    public boolean checkCollision(BoundingShape bv){
        if(bv instanceof AABoundingRect)
            return checkCollision((AABoundingRect) bv);
        return false;
    }

    public abstract boolean checkCollision(AABoundingRect box);

    public abstract void updatePosition(float newX, float newY);

    public abstract void movePosition(float x, float y);
    public abstract float getX();
    public abstract void setX(float newVal);
    public abstract float getY();
    public abstract void setY(float newVal);
}