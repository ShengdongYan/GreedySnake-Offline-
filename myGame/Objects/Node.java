package myGame.Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import myGame.Frames.Contants;

public class Node {
    private Color color;
    private int height, width, x,y;
    private boolean visible;



    public  Node(){
        this.init();
    }

    public void init(){
       this.setX((int) Contants.WIDTH/2);
       this.setY((int)Contants.HEIGHT/2);
       this.height = Contants.UnitHeight;
       this.width = Contants.UnitWidth;
       this.setVisible(true);
       this.color = Color.WHITE;

    }
    public void draw(GraphicsContext gc){
        gc.setFill(color);
        gc.fillOval(getX(),getY(),getWidth(),getHeight());

    }
    public void update(){
        if(!isVisible())
        {return;}

        this.setX((int)(30+Math.random()*(Contants.WIDTH-60)));
        this.setY((int)(30+Math.random()*(Contants.HEIGHT-60)));
        this.setVisible(true);
        double red = (double)(1+Math.random()*255)%1;  // 防止小数点后位数太多,先取整，后转化
        double green = (1+Math.random()*255)%1;
        double blue = (1+Math.random()*255)%1;
        this.color = Color.color( red,green,blue);

    }

    public Color getColor() {
        return color;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}
