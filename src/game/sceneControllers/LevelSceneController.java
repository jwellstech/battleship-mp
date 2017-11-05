package game.sceneControllers;

import game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LevelSceneController {

    @FXML
    private Button pauseButton;

    @FXML
    void pauseGame(ActionEvent event) {
        Stage levelStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        levelStage.setScene(Game.PauseMenu);
        levelStage.show();
    }

}

