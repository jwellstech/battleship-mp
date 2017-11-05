package game.state;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import game.SingletonsCreator;
import game.gameObjects.Submarine;
import game.level.Level;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static javafx.scene.input.KeyCode.UP;


public class LevelState extends State {
    static Submarine playerSub = SingletonsCreator.getOrCreatePlayerSubmarineFactoryMethod();
    static Group UI = new Group();
//    public static final KeyCode ACCELERATE = UP;

    public LevelState() {
        super(UI);
        UI.getChildren().add(new Button("test"));
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        playerSub.setAccelerate(true);
                        break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        playerSub.setAccelerate(false);
                        break;
                }
            }
        });

    }
}
