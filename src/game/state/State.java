package game.state;

import game.Settings;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class State {
    public Scene scene;

    State() {
    }

    public void setScene(Scene newScene) {
        scene = newScene;
    }
    public Scene getScene() {
        return scene;
    }
}
