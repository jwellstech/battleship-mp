package game.state;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import game.Game;
import game.SingletonsCreator;
import game.gameObjects.Submarine;
import game.level.Level;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static javafx.scene.input.KeyCode.UP;


public class LevelState extends State {
    static Submarine playerSub = SingletonsCreator.getOrCreatePlayerSubmarineFactoryMethod();
//    public static final KeyCode ACCELERATE = UP;

    @Override
    public void setScene(Scene newScene) {
        super.setScene(newScene);
    }
    public Submarine getPlayerSub() {
        return playerSub;
    }
}
