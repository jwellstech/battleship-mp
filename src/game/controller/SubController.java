package game.controller;

import game.gameObjects.Submarine;

public abstract class SubController {

    protected Submarine submarine;

    public SubController(Submarine submarine) {
        this.submarine = submarine;
    }


}
