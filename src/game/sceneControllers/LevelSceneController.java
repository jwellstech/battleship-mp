package game.sceneControllers;

import game.Game;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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

    public void sonarFind(boolean[] found) {
        for (int i = 0; i < found.length; i++) {
            if (found[i]) {
                    animate(i+1);
            }
        }
    }

    void animate(int i) {
        ImageView img = sector1;
        switch(i) {
            case 1:
                img = sector1;
                break;
            case 2:
                img = sector2;
                break;
            case 3:
                img = sector3;
                break;
            case 4:
                img = sector4;
                break;
            case 5:
                img = sector5;
                break;
            case 6:
                img = sector6;
                break;
            case 7:
                img = sector7;
                break;
            case 8:
                img = sector8;
                break;
        }
        FadeTransition ft = new FadeTransition(Duration.seconds(1.5), img);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        ft.play();
    }

}
