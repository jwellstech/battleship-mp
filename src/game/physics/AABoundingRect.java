package game.physics;

public class AABoundingRect extends BoundingShape {
    public float x;
    public float y;
    public float width;
    public float height;

    public AABoundingRect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public float getX() {
        return x;
    }
    public void setX(float newVal) {
        x = newVal;
    }
    public float getY() {
        return y;
    }
    public void setY(float newVal) {
        y = newVal;
    }
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
    public void updatePosition(float newX, float newY) {
        x = newX;
        y = newY;
    }

    public void movePosition(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public boolean checkCollision(AABoundingRect rect) {
        return !(rect.x > this.x+width || rect.x+rect.width < this.x || rect.y > this.y+height || rect.y+rect.height < this.y);
    }
}
