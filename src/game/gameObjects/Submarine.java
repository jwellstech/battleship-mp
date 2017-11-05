package game.gameObjects;

import game.Settings;
import game.SingletonsCreator;
import game.level.Level;
import game.level.LevelObject;
import game.physics.AABoundingRect;

import java.util.ArrayList;

public class Submarine extends LevelObject {
    private static Level level  = SingletonsCreator.getOrCreateLevelFactoryMethod();
    private static float height = Settings.SUBMARINE_HEIGHT;
    private static float width  = Settings.SUBMARINE_WIDTH;
    private static float max_velocity = Settings.SUBMARINE_MAX_VELOCITY;
    private static float acceleration  = Settings.SUBMARINE_ACCELERATION;
    private static float deceleration  = Settings.SUBMARINE_DECELERATION;
    private static boolean accelerate = false;
    private static float coolDown = 0;
    private boolean[] foundOctants = new boolean[8];


    public Submarine(float initX, float initY) {
        super(initX, initY, height, width);
        level.registerLevelObject(this);
        ping(level.getLevelObjects());
    }
    public void fire() {
        //TODO: generate torpedo and fire it
        Torpedo torpedo = new Torpedo(getX(), getY());
        torpedo.setDirection(getDirection());
        torpedo.setLauncher(this);
        setCooldown(Settings.SUBMARINE_COOLDOWN);

    }
    public float getCooldown() {
        return coolDown;
    }
    public void setCooldown(float newVal) {
        coolDown = newVal;
    }
    public float getAcceleration(long delta) {
        return acceleration / 10/** delta*/;
    }
    public float getDeceleration(long delta) {
        return deceleration / 10/** delta*/;
    }
    @Override
    public void update(long delta) {
        if(accelerate) {
            if(getVelocity() < max_velocity) {
                setVelocity(getVelocity() + getAcceleration(delta));
            }
            if(getVelocity() > max_velocity) {
                setVelocity(max_velocity);
            }
        }
        else {
            if(getVelocity() > 0) {
                setVelocity(getVelocity() - getDeceleration(delta));
            }
            if(getVelocity() < 0) {
                setVelocity(0);
            }
        }
        if(coolDown > 0) {
            setCooldown(getCooldown() - 20);
            System.out.println(getCooldown());
        }

//        System.out.println("Player pos: " + getX() + ", " + getY() + ", " + getDirection());
        super.update(delta);
    }
    public void setAccelerate(boolean newVal) {
        accelerate = newVal;
    }

    public void ping(ArrayList<LevelObject> objects) {
        for (int i = 0; i < objects.size(); i++) {
            LevelObject lo = objects.get(i);
            if (objects.get(i) != this && checkCollisionInRadius((AABoundingRect) lo.getBoundingShape(), Settings.PING_RADIUS)) {
                System.out.println("Found something!");
                float loX = lo.getX() + ((AABoundingRect) lo.getBoundingShape()).getWidth();
                float loY = lo.getY() + ((AABoundingRect) lo.getBoundingShape()).getHeight();
                float subX = getX() + ((AABoundingRect) getBoundingShape()).getWidth();
                float subY = getY() + ((AABoundingRect) getBoundingShape()).getHeight();
                anglePingedAt((float) Math.tan((loY - subY) / (loX - subX)));
            }
            //System.out.println("Octants: " + foundOctants);
        }
    }
    public boolean[] getFoundOctants() {
        return foundOctants;
    }
    public void anglePingedAt(float angle) {
        if(angle < 0) {
            angle = Math.abs(angle);
            foundOctants[7 - ((int)((angle - (angle % (Math.PI/4))) / (Math.PI/4)))%8] = true;
        }
        else {
            foundOctants[((int)((angle - (angle % (Math.PI/4))) / (Math.PI/4)))%8] = true;
        }
    }
    public void resetPingedOctants() {
        for(int i = 0; i < foundOctants.length; i++) {
            foundOctants[i] = false;
        }
    }

}
