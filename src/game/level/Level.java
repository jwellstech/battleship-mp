package game.level;

import java.util.ArrayList;

public class Level {
    private int levelRadius;
    private ArrayList<LevelObject> levelObjects = new ArrayList<>();
    private ArrayList<LevelObject> collidables = new ArrayList<>();

    public Level() {

    }

    public void registerLevelObject(LevelObject newObject, boolean collidable) {
        levelObjects.add(newObject);
        if(collidable) {
            collidables.add(newObject);
        }
    }

    public ArrayList<LevelObject> getCollidables() {
        return collidables;
    }
    public ArrayList<LevelObject> getLevelObjects() {
        return levelObjects;
    }
}
