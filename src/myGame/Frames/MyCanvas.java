package myGame.Frames;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private enum GameState{READY, PAUSE, RUN, TIMEOUT};
    private Snake snakeA;
    private  Snake snakeB;
    private Node node;
    private GraphicsContext gc;
    private Timeline timeline;
    private KeyFrame keyFrame;
    private SnakeBody snakeBodyA;
    private TimeCounter timeCounter;
    private GameState gameState;


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
        gameState=GameState.RUN;
    }


    public void onKeyPressed(KeyEvent event) {
        KeyCode key = event.getCode();
       if(key==KeyCode.SPACE){
           setGameState(GameState.PAUSE);
       }
       else if(key == KeyCode.ENTER){
          timeline.play();
           setGameState(GameState.RUN);
       }
       else{
        snakeA.onKeyPressed(event);
       }

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

            gc.setStroke(Color.web("#111", 0.5));
            gc.strokeLine(i,0,i,Contants.HEIGHT);
        }
        for (int i = 0; i < Contants.HEIGHT; i += 18) {

        gc.setStroke(Color.web("#111", 0.5));
        gc.strokeLine(0,i,Contants.WIDTH,i);

    }
}
public  void drawStart(GraphicsContext gc){


}

  public void drawPause(GraphicsContext gc){
      gc.setFont(Font.font("Times", FontWeight.BOLD, 80));
       gc.setFill(Color.WHITE);
      gc.fillText("Wait For a While...", Contants.WIDTH/2 - 250, Contants.HEIGHT/2);
      gc.setFont(Font.font("Times", FontWeight.BOLD, 30));
      gc.setFill(Color.GREEN);
      gc.fillText("Press Enter to back", Contants.WIDTH/2 - 100, Contants.HEIGHT/2+100);
      gc.setFont(Font.font("Times", FontWeight.BOLD, 20));
      gc.setFill(Color.RED);
      gc.fillText("Press Esc to Give up", Contants.WIDTH/2 - 80, Contants.HEIGHT/2+200);

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
                if(getGameState()==GameState.RUN){
                draw(gc);
                update();
                }
               else if(getGameState()==GameState.PAUSE){
                   timeline.pause();
                    drawPause(gc);
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


    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
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
