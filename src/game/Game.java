package game;

import game.sceneControllers.LevelSceneController;
import game.state.LevelState;
import game.state.StateComposer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Game extends Application {

    private Image img;
    private Node img2;
    private static final String image_location = "place";

    public static Scene LevelScene;
    public static Scene StartMenu;
    public static Scene PauseMenu;

    static LevelSceneController levelSceneController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {


        //what our stage needs
        /*
        - create the background processes
            - game controller
            - create scene
        - create scene from factory method
        -
         */
        //example
//        primaryStage.setTitle("Hello World!");
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        primaryStage.setScene(new Scene(root, 300, 250));
//        primaryStage.show();
        StateComposer composer = SingletonsCreator.getOrCreateStateComposerFactoryMethod();

        Parent rt = FXMLLoader.load(getClass().getResource("StartMenuScene.fxml"));
        StartMenu = new Scene(rt, 1280, 720);
        rt = FXMLLoader.load(getClass().getResource("PauseMenuScene.fxml"));
        PauseMenu = new Scene(rt, 1280, 720);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelScene.fxml"));
        rt = loader.load();
        levelSceneController = loader.getController();
        LevelScene = new Scene(rt, 1280, 720);
        LevelState levelState = SingletonsCreator.getOrCreateLevelStateFactoryMethod();
        levelState.setScene(LevelScene);

        LevelScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case UP:
                        levelState.getPlayerSub().setAccelerate(true);
                        break;
                    case LEFT:
                        levelState.getPlayerSub().doRotate(1);
                        break;
                    case RIGHT:
                        levelState.getPlayerSub().doRotate(-1);
                        break;
                    case ESCAPE:
                        primaryStage.setScene(Game.PauseMenu);
                        break;
                    case SPACE:
                        levelState.getPlayerSub().ping(SingletonsCreator.getOrCreateLevelFactoryMethod().getLevelObjects());
                        levelSceneController.sonarFind(levelState.getPlayerSub().getFoundOctants());
                        levelState.getPlayerSub().resetFoundOctants();
                        break;
                    case F:
                        if(levelState.getPlayerSub().getCooldown() == 0){
                            levelState.getPlayerSub().fire();
                        }
                }
                double rotate = - Math.toDegrees(levelState.getPlayerSub().getDirection()) + 90;
                levelSceneController.playerSubImage.setRotate(rotate);
            }
        });
        levelState.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case UP:
                        levelState.getPlayerSub().setAccelerate(false);
                        break;
                }
            }
        });

        primaryStage.setScene(StartMenu);
        primaryStage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(primaryStage.getScene() == LevelScene) {
                    composer.update(now);
                }

            }
        };
        timer.start();
    }

}
