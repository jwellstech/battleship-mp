package game.gameObjects;

import game.Settings;
import game.SingletonsCreator;
import game.level.Level;
import game.level.LevelObject;
import game.physics.AABoundingRect;
import game.sound.JOALSoundObject;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public class Submarine extends LevelObject {
    private static Level level  = SingletonsCreator.getOrCreateLevelFactoryMethod();
    private static float height = Settings.SUBMARINE_HEIGHT;
    private static float width  = Settings.SUBMARINE_WIDTH;
    private static float max_velocity = Settings.SUBMARINE_MAX_VELOCITY;
    private static float acceleration  = Settings.SUBMARINE_ACCELERATION;
    private static float deceleration  = Settings.SUBMARINE_DECELERATION;
    private static boolean accelerate = false;
    private static float torpedoCoolDownTimer = 0;
    private static float pingCoolDownTimer = 0;
    JOALSoundObject listener;
    String sound_source = "ping";




    private boolean[] foundOctants = new boolean[8];
    String musicFile;      // For example

    Media sound;
    MediaPlayer mediaPlayer;


    public Submarine(float initX, float initY) {
        super(initX, initY, height, width);
        System.out.println("test1");
//        musicFile = "resources/sfx/ping.mp3";
//        sound = new Media(new File(musicFile).toURI().toString());
//        mediaPlayer = new MediaPlayer(sound);

        System.out.println("test2");
        level.registerLevelObject(this);

        System.out.println("test3");
        initListener();

        System.out.println("test4");
    }
    public void initListener() {
        listener = SingletonsCreator.getOrCreateJOALSoundObjectFactoryMethod();
        listener.setListenerPos(getX()-1, getY());
    }
    public void fire() {
        Torpedo torpedo = new Torpedo(getX(), getY());
        torpedo.setDirection(getDirection());
        torpedo.setLauncher(this);
        setTorpedoCoolDownTimer(Settings.SUBMARINE_FIRE_COOLDOWN);

    }
    public float getPingCoolDownTimer() {
        return pingCoolDownTimer;
    }
    public void setPingCoolDownTimer(float newVal) {
        torpedoCoolDownTimer = newVal;
    }
    public float getTorpedoCoolDownTimer() {
        return torpedoCoolDownTimer;
    }
    public void setTorpedoCoolDownTimer(float newVal) {
        torpedoCoolDownTimer = newVal;
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
        if(getTorpedoCoolDownTimer() > 0) {
            setTorpedoCoolDownTimer(getTorpedoCoolDownTimer() - 20);
        }
        if(getPingCoolDownTimer() > 0) {
            setPingCoolDownTimer(getPingCoolDownTimer() - 20);
        }

//        System.out.println("Player pos: " + getX() + ", " + getY() + ", " + getDirection());
        super.update(delta);
    }
    public void setAccelerate(boolean newVal) {
        accelerate = newVal;
    }

    public void ping(ArrayList<LevelObject> objects) {
        setPingCoolDownTimer(Settings.SUBMARINE_PING_COOLDOWN);
        for (int i = 0; i < objects.size(); i++) {
            LevelObject lo = objects.get(i);
            if (objects.get(i) != this && checkCollisionInRadius((AABoundingRect) lo.getBoundingShape(), Settings.PING_RADIUS)) {
                System.out.println("Found something!");
                float loX = lo.getX();// + ((AABoundingRect) lo.getBoundingShape()).getWidth()/2;
                float loY = lo.getY();// + ((AABoundingRect) lo.getBoundingShape()).getHeight()/2;
                float subX = getX();// + ((AABoundingRect) getBoundingShape()).getWidth()/2;
                float subY = getY();// + ((AABoundingRect) getBoundingShape()).getHeight()/2;
                anglePingedAt((float) Math.tan((loY - subY) / (loX - subX)));
            }
            //System.out.println("Octants: " + foundOctants);
        }
        listener.load(sound_source, false);
        listener.play(sound_source);
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
    public void resetFoundOctants() {
        for(int i = 0; i < foundOctants.length; i++) {
            foundOctants[i] = false;
        }
    }
    public void playMedia() {
        new MediaPlayer(sound).play();
    }
    @Override
    public void move(long delta) {
        super.move(delta);
        listener.setListenerPos(getX(), getY());
        listener.setPos(sound_source, getX() - 1, 0, getY());
    }
    @Override
    public void rotate(float degrees) {
        setDirection((float)((getDirection() + (float)Math.toRadians(degrees)) % (2*Math.PI)));
        //TODO: make consistent with direction of the submarine
        listener.turnListener((int)degrees);
    }

}
