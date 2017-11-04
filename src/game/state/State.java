package game.state;

import game.Settings;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class State {
    public Scene scene;

    State(Object root) {
        scene = new Scene((Parent) root, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
    }
}
