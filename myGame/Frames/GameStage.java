package myGame.Frames;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import myGame.Objects.Snake;


import java.util.LinkedList;

public class GameStage extends Application {
    private enum GameState{READY, PAUSE, RUN, TIMEOUT};
    private Timeline timeline;
    private KeyFrame keyFrame;
    public  Stage stage = new Stage();
    public  Group root;
    public   LinkedList<Light.Point> list = new LinkedList<Light.Point>();
    public static Snake snake;
    public  GameState gameState;
    public MyCanvas gameCanvas;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage=primaryStage;
        primaryStage.setTitle("Greedy Snake!");
         root = new Group();

        /*Canvas canvas = new Canvas(Contants.WIDTH, Contants.HEIGHT);
        GraphicsContext graphic = canvas.getGraphicsContext2D();*/

        gameCanvas = new MyCanvas(root);
        root.getChildren().add(gameCanvas);

        Scene scene = new Scene(root);
        scene.setFill(Color.rgb(60,60,60));

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gameCanvas.onKeyPressed(event);
            }});

        gameCanvas.initial();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);

    }
}
