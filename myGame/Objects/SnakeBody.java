package myGame.Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light;
import myGame.Frames.Contants;

import java.util.LinkedList;

public class SnakeBody {

    private LinkedList<Light.Point> pointlist = new LinkedList<Light.Point>();
    private  Snake snake ;
    private boolean visible;
    private double x;
    private double y;
    private  double height;
    private  double width;
    private int length;



    public SnakeBody(Snake snake){

         this.snake = snake;
         initBody();
         setVisible(true);
         x=snake.getX();
         y=snake.getY();
         length= Contants.DEFAULT_LENGTH;
     }
     public void initBody(){
          pointlist.clear();
          length= Contants.DEFAULT_LENGTH;
           for (int i = 0; i < getLength(); i++) {
             Light.Point point = new Light.Point();
             point.setX(getX() );
             point.setY(getY()+ Contants.DISTANCE * i);
             pointlist.add(point);
         }
     }


    public void draw(GraphicsContext gc) {

        // 原理：移动一次，那么后一个的位置就等于前一个的位置，也就是加入新的first，删除旧的last
        Light.Point firstPoi = pointlist.getFirst();
        // 位移已经达到一个身位再进行处理
        if (firstPoi.getX() + Contants.DISTANCE <= getX() || firstPoi.getX() - Contants.DISTANCE >= getX()
                || firstPoi.getY() + Contants.DISTANCE <= getY() || firstPoi.getY() - Contants.DISTANCE >= getY()) {
            Light.Point poi = new Light.Point();
            poi.setX(getX());
            poi.setY(getY());
            // 添加第一个头
            pointlist.addFirst(poi);
            // 如果吃到了就不移除了，没吃到就删除最后一个
            if (this.length < snake.getLength()) {
                this.length = this.length + 1;
            } else {
                // 移除最后一个
                pointlist.removeLast();
            }
        }

        gc.setFill(snake.getColor());
        for (Light.Point point : pointlist) {
            gc.fillOval(point.getX(), point.getY(), getWidth(), getHeight());
        }
    }

    public void update() {
        // 如果不显示了，重新展示
        if (!isVisible()) {
            initBody();
            setVisible(true);
            return;
        }
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Snake getSnake() {
        return snake;
    }

    public boolean isVisible() {
        return visible;
    }

    public LinkedList<Light.Point> getPointlist() {
        return pointlist;
    }

    public void setPointlist(LinkedList<Light.Point> pointlist) {
        this.pointlist = pointlist;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public double getX() {
        return snake.getX();
    }

    public double getY() {
        return snake.getY();
    }

    public double getHeight() {
        return snake.getHeight();
    }

    public double getWidth() {
        return snake.getWidth();
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
