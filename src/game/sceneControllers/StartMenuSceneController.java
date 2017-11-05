package game.sceneControllers;

import game.Game;
import game.SingletonsCreator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;

public class StartMenuSceneController {

    @FXML
    private Button startButton;

    @FXML
    void startGame(ActionEvent event) {
        Stage startStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        SingletonsCreator.resetLevelComponents();
        startStage.setScene(Game.LevelScene);
        startStage.show();
    }

}
