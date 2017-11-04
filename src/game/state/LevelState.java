package game.state;

import game.SingletonsCreator;
import game.level.Level;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class LevelState extends State {

    static Group UI = new Group();

    public LevelState() {
        super(UI);
        UI.getChildren().add(new Button("test"));
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode().getName()) {
                    default:
                        System.out.println("d pressed");
                        System.exit(0);
                        break;
                }
            }
        });

    }
}
