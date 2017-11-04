package game.gameObjects;

import game.Settings;
import game.level.LevelObject;

import java.util.Set;

public class Torpedo extends LevelObject {
    private static float width      = Settings.TORPEDO_WIDTH;
    private static float height     = Settings.TORPEDO_HEIGHT;
    private static float velocity   = Settings.TORPEDO_VELOCITY;

    private        int   damage = Settings.TORPEDO_DAMAGE;

    public Torpedo(float initX, float initY) {
        super(initX, initY, width, height);
    }

    public int getDamage() {
        return damage;
    }

}
