package game.sceneControllers;

import game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartMenuSceneController {

    @FXML
    private Button startButton;

    @FXML
    void startGame(ActionEvent event) {
        Stage startStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        startStage.setScene(Game.LevelScene);
        startStage.show();
    }

}
