package game;

import game.gameObjects.Submarine;
import game.level.Level;
import game.state.LevelState;

public class SingletonsCreator {
    static Submarine playerSubmarine;
    static LevelState levelState;
    static Level level;

    public static Submarine getOrCreatePlayerSubmarineFactoryMethod() {
        if(playerSubmarine == null) {
            //TODO: give actual starting values based on size of map
            playerSubmarine = new Submarine(0, 0);
        }
        return playerSubmarine;
    }

    public static LevelState getOrCreateLevelStateFactoryMethod() {
        if(levelState == null) {
            levelState = new LevelState();
        }
        return levelState;
    }
    public static Level getOrCreateLevelFactoryMethod() {
        if(level == null) {
            level = new Level();
        }
        return level;
    }

}
