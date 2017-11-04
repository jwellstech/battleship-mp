package game;

import game.gameObjects.Submarine;

public class SingeltonsCreator {
    static Submarine playerSubmarine;

    public static Submarine getOrCreatePlayerSubmarineFactoryMethod() {
        if(playerSubmarine == null) {
            //TODO: give actual starting values based on size of map
            playerSubmarine = new Submarine(0, 0);
        }
        return playerSubmarine;
    }

//    public static getOrCreateLevelStateFactoryMethod() {}
}
