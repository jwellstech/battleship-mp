package game.sceneControllers;

import game.Game;
import game.Settings;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
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
    private ImageView dotMatrix;

    @FXML
    private ImageView torpedoIndicator;

    @FXML
    private ImageView pingIndicator;

    @FXML
    private Text gameOver;

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

    public void torpedoRefresh() {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), torpedoIndicator);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setCycleCount((int) Settings.SUBMARINE_FIRE_COOLDOWN / 500);
        ft.setAutoReverse(true);
        ft.play();
    }

    public void sonarRefresh() {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), pingIndicator);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setCycleCount((int) Settings.SUBMARINE_PING_COOLDOWN / 500);
        ft.setAutoReverse(true);
        ft.play();
    }

    public void moveDots(float xVel, float yVel) {
        dotMatrix.setX((dotMatrix.getX() - xVel) % 35);
        dotMatrix.setY((dotMatrix.getY() + yVel) % 35);
    }

    public void death() {
        gameOver.setOpacity(1);
    }
}
