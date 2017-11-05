package game.gameObjects;

import game.Settings;
import game.SingletonsCreator;
import game.level.Level;
import game.level.LevelObject;

public class Mine extends LevelObject {
    private static Level level      = SingletonsCreator.getOrCreateLevelFactoryMethod();

    private static float width  = Settings.MINE_WIDTH;
    private static float height = Settings.MINE_HEIGHT;

    public Mine(float initX, float initY) {
        super(initX, initY, width, height);
        level.registerLevelObject(this);
    }
    public float getExplosionRadius() {
        return Settings.EXPLOSION_RADIUS;
    }
    public static void generateMineField(int radius, int density) {
        for(int i = 0; i < density; i++) {
            double u = Math.random();
            double v = Math.random();
            double w = radius * Math.sqrt(u);
            double t = 2 * Math.PI * v;
            new Mine((float) (w * Math.cos(t)), (float)(w * Math.sin(t)));
        }
    }

}
