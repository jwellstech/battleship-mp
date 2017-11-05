package game.sceneControllers;

import game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PauseMenuSceneController {

    @FXML
    private Button resumeButton;

    @FXML
    void resumeGame(ActionEvent event) {
        Stage pauseStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        pauseStage.setScene(Game.LevelScene);
        pauseStage.show();
    }

    @FXML
    void exitGame(ActionEvent event) {
        Stage pauseStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        pauseStage.setScene(Game.StartMenu);
        pauseStage.show();
    }

}

