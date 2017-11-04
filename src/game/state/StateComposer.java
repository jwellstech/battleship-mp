package game.state;

import game.SingletonsCreator;
import game.gameObjects.Submarine;
import game.gameObjects.Torpedo;
import game.level.Level;

public class StateComposer {
    State activeState;
    private static  LevelState  levelState  = SingletonsCreator.getOrCreateLevelStateFactoryMethod();

    private static  Level       level       = SingletonsCreator.getOrCreateLevelFactoryMethod();
    private         boolean     running     = true;
    long time;

    /*
    //update();
    //actriveLevel;
    //way to pause old acvtive state
    //update will grb level objects from level in each state
    //will update all of those levelobjects
    //collisionChecking for subs versus

     */
    public StateComposer() {
        //run on initialization
        activeState = levelState;
    }
    public void init(long now) {
        time = now;
    }
    public void update(long now) {
        long delta = (now-time)/1000;
        if(running) {
            for(Submarine submarine: level.getSubmarines()) {
                submarine.move(delta);
            }
            for(Torpedo torpedo: level.getTorpedoes()) {
                torpedo.move(delta);
            }
        }
        level.checkCollision();
        time = now;
    }
    public State getActiveState() {
        return activeState;
    }
}
