package game.sceneControllers;

import game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class LevelSceneController {

    @FXML
    private ImageView sector5;

    @FXML
    private ImageView sector4;

    @FXML
    private ImageView sector3;

    @FXML
    private ImageView sector2;

    @FXML
    private ImageView sector1;

    @FXML
    private ImageView sector8;

    @FXML
    private ImageView sector7;

    @FXML
    public ImageView playerSubImage;

    @FXML
    private ImageView sector6;

    @FXML
    private Button pauseButton;

    @FXML
    void pauseGame(ActionEvent event) {
        Stage levelStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        levelStage.setScene(Game.PauseMenu);
        levelStage.show();
    }

}
