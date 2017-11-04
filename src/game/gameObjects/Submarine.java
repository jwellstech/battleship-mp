package game.gameObjects;

import game.Settings;
import game.SingeltonsCreator;
import game.level.Level;
import game.level.LevelObject;

public class Submarine extends LevelObject {
    private static Level level  = SingeltonsCreator.getOrCreateLevelFactoryMethod();
    private static float height = Settings.SUBMARINE_HEIGHT;
    private static float width  = Settings.SUBMARINE_WIDTH;

    public Submarine(float initX, float initY) {
        super(initX, initY, height, width);
    }
    public void fire() {
        //TODO: generate torpedo and fire it
        Torpedo torpedo = new Torpedo(getX(), getY());
        torpedo.setDirection(getDirection());

    }


}
