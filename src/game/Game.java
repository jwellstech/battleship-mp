package game;

import game.sceneControllers.LevelSceneController;
import game.state.StateComposer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOError;
import java.io.IOException;

public class Game extends Application {

    private Image img;
    private Node img2;
    private static final String image_location = "place";

    public static Scene LevelScene;
    public static Scene StartMenu;
    public static Scene PauseMenu;

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
        StartMenu = new Scene(rt, 900, 600);
        rt = FXMLLoader.load(getClass().getResource("PauseMenuScene.fxml"));
        PauseMenu = new Scene(rt, 900, 600);
        rt = FXMLLoader.load(getClass().getResource("LevelScene.fxml"));
        LevelScene = new Scene(rt, 900, 600);
        LevelScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case ESCAPE:
//                       composer.setRunning(false);
//                        Stage levelStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        primaryStage.setScene(Game.PauseMenu);
//                        primaryStage.show();
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
