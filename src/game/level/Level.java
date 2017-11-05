package game.level;

import game.Settings;
import game.gameObjects.Mine;
import game.gameObjects.Submarine;
import game.gameObjects.Torpedo;
import game.physics.AABoundingRect;
import game.physics.BoundingShape;

import java.util.ArrayList;

public class Level {
    private ArrayList<Submarine> submarines;
    private ArrayList<Torpedo> torpedoes;
    private ArrayList<Mine> mines;

    public Level() {
        submarines = new ArrayList<>();
        torpedoes = new ArrayList<>();
        mines = new ArrayList<>();
    }



    //TODO: consider revision
    public void registerLevelObject(LevelObject newObject) {
        System.out.println(newObject.getClass() + ": (" + newObject.getX() + ", " + newObject.getY() + ")'");
        if(newObject instanceof Submarine) {
            submarines.add((Submarine) newObject);
            for(Mine mine: getMines()) {
                if(mine.getBoundingShape().checkCollision(newObject.getBoundingShape())) {
                    mines.remove(mine);
                }
            }
        }
        if(newObject instanceof Torpedo) {
            torpedoes.add((Torpedo) newObject);
        }
        if(newObject instanceof Mine) {
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
            while (cont && j < submarines.size()) {
                if(submarines.get(i).getBoundingShape().checkCollision(submarines.get(j).getBoundingShape())) {
                    submarines.remove(j);
                    submarines.remove(i);
                    i--;
                    cont = false;
                }
                j++;
            }
            j = 0;
            while(cont && j < torpedoes.size()) {
                if(submarines.get(i).getBoundingShape().checkCollision(torpedoes.get(j).getBoundingShape()) &&
                        !submarines.get(i).getBoundingShape().checkCollision(torpedoes.get(j).getLauncher().getBoundingShape())) {
                    submarines.remove(i);
                    torpedoes.remove(j);
                    i--;
                    cont = false;
                }
                j++;
            }
            j = 0;
            while(cont && j < mines.size()) {
                if(submarines.get(i).getBoundingShape().checkCollision(mines.get(j).getBoundingShape())) {
                    submarines.remove(i);
                    explode(mines.get(j));
                    mines.remove(j);
                    i--;
                    cont = false;
                }
                j++;
            }
        }
        for(int i = 0; i < torpedoes.size(); i++) {
            int j = 0;
            boolean cont = true;
            while(cont && j < mines.size()) {
                if(torpedoes.get(i).getBoundingShape().checkCollision(mines.get(j).getBoundingShape())) {
                    torpedoes.remove(i);
                    explode(mines.get(j));
                    mines.remove(j);
                    i--;
                    cont = false;
                }
                j++;
            }
        }
    }
    public void explode(Mine mine) {
        for(int i = 0; i < submarines.size(); i++) {
            if(checkCollisionInRadius((AABoundingRect)submarines.get(i).getBoundingShape(), mine)) {
                submarines.remove(i);
                i--;
            }
        }
    }
    public boolean checkCollisionInRadius(AABoundingRect rect, Mine mine) {
        float mineX = mine.getX() + ((AABoundingRect)mine.getBoundingShape()).getWidth()/2;
        float mineY = mine.getX() + ((AABoundingRect)mine.getBoundingShape()).getWidth()/2;
        if(Math.hypot(rect.getX() - mineX, rect.getY() - mineY ) <= mine.getExplosionRadius()) {
            return true;
        }
        if(Math.hypot(rect.getX() + rect.getWidth() - mineX, rect.getY() - mineY ) <= mine.getExplosionRadius()) {
            return true;
        }
        if(Math.hypot(rect.getX() - mineX, rect.getY() + rect.getHeight() - mineY ) <= mine.getExplosionRadius()) {
            return true;
        }
        if(Math.hypot(rect.getX() + rect.getWidth() - mineX, rect.getY() + rect.getHeight() - mineY ) <= mine.getExplosionRadius()) {
            return true;
        }
        return false;
    }
}
