package game.level;

import game.gameObjects.Mine;
import game.gameObjects.Submarine;
import game.gameObjects.Torpedo;
import game.physics.AABoundingRect;

import java.util.ArrayList;

public class Level {
    private int levelRadius;
    private ArrayList<Submarine> submarines = new ArrayList<>();
    private ArrayList<Torpedo> torpedoes = new ArrayList<>();
    private ArrayList<Mine> mines = new ArrayList<>();

    public Level() {

    }
    //TODO: consider revision
    public void registerLevelObject(LevelObject newObject) {
        if(newObject instanceof Submarine) {
            submarines.add((Submarine) newObject);
        }
        if(newObject instanceof Submarine) {
            torpedoes.add((Torpedo) newObject);
        }
        if(newObject instanceof Submarine) {
            mines.add((Mine) newObject);
        }
    }
    public ArrayList<Submarine> getSubmarines() {
        return submarines;
    }
    public ArrayList<Torpedo> getTorpedoes() {
        return torpedoes;
    }

    public ArrayList<Mine> getMines() {
        return mines;
    }
    public void checkCollision() {
        //TODO: handle collisions and kill the dead
        //might have errors on deletion
        for(int i = 0; i < submarines.size(); i++) {
            int j = i+1;
            boolean cont = true;
            while (j < submarines.size() && cont) {
                if(submarines.get(i).getBoundingShape().checkCollision(submarines.get(j).getBoundingShape())) {
                    submarines.remove(j);
                    submarines.remove(i);
                    i--;
                    cont = false;
                }
                j++;
            }
            j = 0;
            while(j < torpedoes.size() && cont) {
                if(submarines.get(i).getBoundingShape().checkCollision(torpedoes.get(j).getBoundingShape())) {
                    submarines.remove(i);
                    torpedoes.remove(j);
                    i--;
                    cont = false;
                }
            }
            j = 0;
            while(j < mines.size() && cont) {
                if(submarines.get(i).getBoundingShape().checkCollision(mines.get(j).getBoundingShape())) {
                    submarines.remove(i);
                    explode(mines.get(j));
                    mines.remove(j);
                    i--;
                    cont = false;
                }
            }
        }
        for(int i = 0; i < torpedoes.size(); i++) {
            int j = 0;
            boolean cont = true;
            while(j < mines.size() && cont) {
                if(torpedoes.get(i).getBoundingShape().checkCollision(mines.get(j).getBoundingShape())) {
                    torpedoes.remove(i);
                    explode(mines.get(j));
                    mines.remove(j);
                    i--;
                    cont = false;
                }
            }
        }
    }
    public void explode(Mine mine) {
        float mineX = mine.getX() + ((AABoundingRect)mine.getBoundingShape()).getWidth()/2;
        float mineY;
        for(int i = 0; i < submarines.size(); i++) {

        }
    }
}
