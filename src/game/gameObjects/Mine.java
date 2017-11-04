package game.gameObjects;

import game.Settings;
import game.level.LevelObject;

public class Mine extends LevelObject {
    private static float width  = Settings.MINE_WIDTH;
    private static float height = Settings.MINE_HEIGHT;
    public Mine(float initX, float initY) {
        super(initX, initY, width, height);
    }

}
