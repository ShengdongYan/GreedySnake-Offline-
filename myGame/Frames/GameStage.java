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
    private Timeline timeline;
    private KeyFrame keyFrame;
    public  Stage stage = new Stage();
    public  Group root;
    public   LinkedList<Light.Point> list = new LinkedList<Light.Point>();
    public static Snake snake;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage=primaryStage;
        primaryStage.setTitle("Greedy Snake!");
         root = new Group();

        /*Canvas canvas = new Canvas(Contants.WIDTH, Contants.HEIGHT);
        GraphicsContext graphic = canvas.getGraphicsContext2D();*/

        MyCanvas gameCanvas = new MyCanvas();
        gameCanvas.initial();


        root.getChildren().add(gameCanvas);
        Scene scene = new Scene(root);
        scene.setFill(Color.rgb(60,60,60));
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gameCanvas.onKeyPressed(event);
            }});

    /*   scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
        //            @Override
        //            public void handle(KeyEvent event) {
        //                gameCanvas.onKeyPressed(event);
        //            }
        //        });
        //*/
        //       /*snake = new Snake(60,60);
        //       snake.setColor(Color.WHITE);*/
        //        //snake.drawSnake(graphic);
        //
        //       // gameCanvas.initEvents(root);

        gameCanvas.start();
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    public static void main(String[] args) {
        System.out.println((int)(Math.random()*Contants.WIDTH));
        launch(args);
    }
}
