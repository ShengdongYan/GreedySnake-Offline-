package myGame.Frames;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import myGame.Objects.Node;
import myGame.Objects.Snake;
import myGame.Objects.SnakeBody;

/**
 *
 * @author Shengdong Yan
 * @version 2019-3-9
 */
public class MyCanvas extends Canvas {
    private Snake snakeA;
    private  Snake snakeB;
    private Node node;
    private GraphicsContext gc;
    private Timeline timeline;
    private KeyFrame keyFrame;
    private SnakeBody snakeBodyA;
    private TimeCounter timeCounter;


    public MyCanvas(){
        node =new Node();
        this.setHeight(Contants.HEIGHT);
        this.setWidth(Contants.WIDTH);
        snakeA = new Snake(Contants.userAX,Contants.userAY,node);
        snakeB = new Snake(Contants.userBX,Contants.userBY,node);
         gc = this.getGraphicsContext2D();
         snakeBodyA = new SnakeBody(snakeA);
       timeCounter = new TimeCounter(300);

    }


    public void  initial(){
        timeCounter.start();
        snakeA.setColor(Color.BLUE);
        snakeBodyA.initBody();
        drawGridding(gc);
        initTimeLine(gc);
    }


 /*   public void initEvents(Group root) {
        getParent().getScene().setOnKeyPressed(event -> {
            onKeyPressed(event);
        });

        getParent().getScene().setOnKeyReleased(event -> {
            onKeyReleased(event);
        });

      *//*  getParent().getScene().setOnMouseMoved(event -> {
            onMouseMoved(event);
        });*//*
    }*/

    public void onKeyPressed(KeyEvent event) {
      snakeA.onKeyPressed(event);
    }

    public void onKeyReleased(KeyEvent event) {
       snakeA.onKeyPressed(event);
    }

 /*   public void onMouseMoved(MouseEvent event) {
        for (SObject wObject : mObjects) {
            wObject.onMouseMoved(event);
        }
    }
*/

    public void drawGridding(GraphicsContext gc){



        for (int i = 0; i < Contants.WIDTH; i += 18) {
           /* Line line = new Line(i, 0, i, Contants.HEIGHT);
            root.getChildren().add(line);*/
            gc.setStroke(Color.web("#111", 0.5));
            gc.strokeLine(i,0,i,Contants.HEIGHT);



        }
        for (int i = 0; i < Contants.HEIGHT; i += 18) {

        gc.setStroke(Color.web("#111", 0.5));
        gc.strokeLine(0,i,Contants.WIDTH,i);

            /*Line line = new Line(0, i, Contants.WIDTH, i);
            line.setStroke(Color.web("#111", 0.5));
            root.getChildren().add(line);*/
    }
}

    public void initTimeLine( GraphicsContext gc) {

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        keyFrame = new KeyFrame(Duration.millis(Contants.duration), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                gc.setEffect(null);
                gc.clearRect(0, 0, getWidth(), getHeight());
                drawGridding(gc);
                draw(gc);
                update();
            }
        });
        timeline.getKeyFrames().add(keyFrame);
    }


    /**
     * start the update timeline
     */
    public void start() {
        timeline.play();
    }

    /**
     * pause the update timeline
     */
    public void pause() {
        timeline.pause();
    }

    /**
     * stop the update timeline
     */
    public void stop() {
        timeline.stop();
    }



    public void draw(GraphicsContext gc){
        snakeBodyA.draw(gc);             // 后期把食物两条蛇的画画过程放这
        snakeA.drawSnake(gc);
        node.draw(gc);
        timeCounter.draw(gc);
    }

    public  void  update(){
        snakeBodyA.update();
        snakeA.update();
        if(snakeA.isGetNode()) {node.update();}
        if(snakeA.isReachBorder()){snakeA.rebirth(); snakeBodyA.initBody();}// 后期把两条蛇和食物的信息更新放在这
    }


    public Snake getSnakeA() {
        return snakeA;
    }

    public Snake getSnakeB() {
        return snakeB;
    }

    public Node getNode() {
        return node;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setSnakeA(Snake snakeA) {
        this.snakeA = snakeA;
    }

    public void setSnakeB(Snake snakeB) {
        this.snakeB = snakeB;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }
    public SnakeBody getSnakeBody() {
        return snakeBodyA;
    }

    public void setSnakeBody(SnakeBody snakeBody) {
        this.snakeBodyA = snakeBody;
    }
}
