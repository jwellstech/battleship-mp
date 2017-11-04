package game.state;

import game.level.Level;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;

public class LevelState extends State {

    static Group UI;
//    static Level level = SingletonsCreator.getOrCreateLevelFactoryMethod();
    LevelState() {
        super(UI);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

            }
        });
    }
//    public Level getLevel() {
//        return level;
//    }
}
