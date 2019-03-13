package myGame.Frames;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    private Snake snakeB;
    private Node node;
    private GraphicsContext gc;
    private Timeline timeline;
    private KeyFrame keyFrame;
    private SnakeBody snakeBodyA;
    private SnakeBody snakeBodyB;
    private TimeCounter timeCounter;
    private Contants.GameState gameState;
    private Group root;
    private Label label;


    public MyCanvas(Group root){

         this.root =root;
    }


    public void  initial(){
        node =new Node();
        this.setHeight(Contants.HEIGHT);
        this.setWidth(Contants.WIDTH);
        snakeA = new Snake(Contants.userAX,Contants.userAY,node,snakeBodyA);
        snakeB = new Snake(Contants.userBX,Contants.userBY,node, snakeBodyB);
        gc = this.getGraphicsContext2D();
        snakeBodyA = new SnakeBody(snakeA);
        snakeBodyB = new SnakeBody(snakeB);
        timeCounter = new TimeCounter(Contants.GameDuration);
        snakeA.setColor(Color.BLUE);
        snakeA.setUserName("DongdongSnake");  // Update the userName here

      //  snakeB.setUserName("ChangeCode!");
        snakeBodyA.initBody();
        initTimeLine(gc);
        drawStart();

    }


    public void onKeyPressed(KeyEvent event){
        KeyCode key = event.getCode();
        // 在run的状态下再按空格，变暂停状态
        if(this.getGameState()==Contants.GameState.RUN){
        if(key==KeyCode.SPACE){
            setGameState(Contants.GameState.PAUSE);
            timeCounter.stopTimer();
        }
        else if(key ==KeyCode.ESCAPE){
                setGameState(Contants.GameState.TIMEOUT);
                timeCounter.setTime(0);
        }
        else{
            snakeA.onKeyPressed(event);
        }
        }

        else if(this.getGameState()==Contants.GameState.PAUSE) {   // 在暂停状态，再一次空格回到run状态
            if (key == KeyCode.SPACE) {
                setGameState(Contants.GameState.RUN);
                timeCounter.startTimer();
            }
        }


    }

/*

    public void onKeyReleased(KeyEvent event) {
       snakeA.onKeyReleased(event);
    }
   public void onMouseMoved(MouseEvent event) {
        for (SObject wObject : mObjects) {
            wObject.onMouseMoved(event);
        }
    }
*/


    public void initTimeLine( GraphicsContext gc) {

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        keyFrame = new KeyFrame(Duration.millis(Contants.duration), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                gc.setEffect(null);
                gc.clearRect(0, 0, getWidth(), getHeight());
                drawGridding(gc);
                if(getGameState()==Contants.GameState.RUN){
                draw(gc);
                update();
                drawScoreBoard();
                }
               else if(getGameState()==Contants.GameState.PAUSE){
                    drawPause(gc);
                }
               else if(getGameState() ==Contants.GameState.TIMEOUT){
                   drawTimeout(gc);
                }
            }
        });
        timeline.getKeyFrames().add(keyFrame);
    }


    /**
     * start the update timeline
     */
    public void start() {
        timeline.play();
        timeCounter.start();
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



    public void drawGridding(GraphicsContext gc){

        for (int i = 0; i < Contants.WIDTH; i += 18) {

            gc.setStroke(Color.web("#111", 0.5));
            gc.strokeLine(i,0,i,Contants.HEIGHT);
        }
        for (int i = 0; i < Contants.HEIGHT; i += 18) {

            gc.setStroke(Color.web("#111", 0.5));
            gc.strokeLine(0,i,Contants.WIDTH,i);

        }
    }

    public  void drawStart() {

        Label label = new Label("Are You Ready?");
        label.setLayoutX(Contants.WIDTH / 2 - 300);
        label.setLayoutY(Contants.HEIGHT / 2 - 250);
        label.setStyle("-fx-font: 80 arial; -fx-font-weight: bold; -fx-text-fill: Gray;  ");
        root.getChildren().add(label);

        Button button = new Button();
        button.setText("Start Now!");
        button.setLayoutX(Contants.WIDTH / 2 - 75);
        button.setLayoutY(Contants.HEIGHT / 2 - 50);
        button.setMinWidth(150);
        button.setMinHeight(70);
        button.setStyle("-fx-font: 24 arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #ff4e4e; -fx-background-radius: 20; ");
        root.getChildren().add(button);

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setGameState(Contants.GameState.RUN);
                root.getChildren().remove(button);
                root.getChildren().remove(label);
                start();
            }
        });
    }

    public void drawScoreBoard(){
        String InfoA = String.format("%-14s", snakeA.getUserName())+": "+String.valueOf(snakeA.getScore());
        String InfoB = String.format("%-14s", snakeB.getUserName())+": "+String.valueOf(snakeB.getScore());
        // Username 从网络得到，顺便加成固定长度(空格大小和字体大小不一样，所以显示的长度还是不一样)

        label = new Label(InfoA+"\n"+ InfoB);
        label.setLayoutX(Contants.WIDTH-300);
        label.setLayoutY(Contants.HEIGHT-800);
        label.setStyle("-fx-font: 30 arial; -fx-font-weight: bold; -fx-text-fill: Gray;  ");
        root.getChildren().add(label);
    }

    public void drawPause(GraphicsContext gc){
        gc.setFont(Font.font("Times", FontWeight.BOLD, 80));
        gc.setFill(Color.YELLOW);
        gc.fillText("Wait For a While...", Contants.WIDTH/2 - 280, Contants.HEIGHT/2 -100);
        gc.setFont(Font.font("Times", FontWeight.BOLD, 30));
        gc.setFill(Color.GREEN);
        gc.fillText("Press Space again to back", Contants.WIDTH/2 - 150, Contants.HEIGHT/2+20);
        gc.setFont(Font.font("Times", FontWeight.BOLD, 20));
        gc.setFill(Color.RED);
        gc.fillText("Press Esc to Give up", Contants.WIDTH/2 - 80, Contants.HEIGHT/2+120);

    }

    public  void drawTimeout(GraphicsContext gc){
        String massage = "Time out";
        if(snakeA.getScore()>snakeB.getScore()){
            massage +="\n"+ snakeA.getUserName()+ " is Winner";
        }
        else massage +="\n"+ snakeA.getUserName()+ " is Winner";

        gc.setFont(Font.font("Times", FontWeight.BOLD, 40));
        gc.setFill(Color.YELLOW);
        gc.fillText(massage, Contants.WIDTH/2 - 300, Contants.HEIGHT/2);

        Button button = new Button();
        button.setText("Ok,Return");
        button.setLayoutX(Contants.WIDTH / 2 - 75);
        button.setLayoutY(Contants.HEIGHT - 150);
        button.setMinWidth(150);
        button.setMinHeight(70);
        button.setStyle("-fx-font: 24 arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #ff4e4e; -fx-background-radius: 20; ");
        root.getChildren().add(button);

       button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

    }


    public void draw(GraphicsContext gc){

        snakeBodyA.draw(gc);             // 后期把食物两条蛇的画画过程放这
        snakeA.drawSnake(gc);
        node.draw(gc);
        timeCounter.draw(gc);
    }

    public  void  update(){
        if(timeCounter.getTime()<=0)
            this.setGameState(Contants.GameState.TIMEOUT);

        snakeBodyA.update();
        snakeA.update();
        if(snakeA.isGetNode()) {node.update();}
        if(snakeA.isReachBorder()){snakeA.rebirth(); snakeBodyA.initBody();} // 后期把两条蛇和食物的信息更新放在这
        root.getChildren().remove(label);
    }



    public Contants.GameState getGameState() {
        return gameState;
    }

    public void setGameState(Contants.GameState gameState) {
        this.gameState = gameState;

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
