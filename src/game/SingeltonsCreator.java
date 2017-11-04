package game;

import game.gameObjects.Submarine;
import game.level.Level;

public class SingeltonsCreator {
    static Submarine playerSubmarine;
    static Level level;

    public static Submarine getOrCreatePlayerSubmarineFactoryMethod() {
        if(playerSubmarine == null) {
            //TODO: give actual starting values based on size of map
            playerSubmarine = new Submarine(0, 0);
        }
        return playerSubmarine;
    }
    public static Level getOrCreateLevelFactoryMethod() {
        if(level == null) {
            level = new Level();
        }
        return level;
    }

}
