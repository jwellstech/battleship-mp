package game.gameObjects;

import game.Settings;
import game.SingletonsCreator;
import game.level.Level;
import game.level.LevelObject;


public class Torpedo extends LevelObject {
    private static Level level      = SingletonsCreator.getOrCreateLevelFactoryMethod();
    private static float width      = Settings.TORPEDO_WIDTH;
    private static float height     = Settings.TORPEDO_HEIGHT;
    private static float velocity   = Settings.TORPEDO_VELOCITY;

    private        int   damage = Settings.TORPEDO_DAMAGE;

    public Torpedo(float initX, float initY) {
        super(initX, initY, width, height);
        level.registerLevelObject(this, false);
    }



}
