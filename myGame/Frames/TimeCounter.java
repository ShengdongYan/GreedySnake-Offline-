package myGame.Frames;

import cn.silence1772.core.SContants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TimeCounter implements Runnable{

    private Thread t;
    private int timeCounter;
    private  boolean visible;

    public TimeCounter(int times) {

        timeCounter = times;
    }

    public  void draw(GraphicsContext gc){

        gc.setFont(Font.font(null, FontWeight.BLACK, 40));
       // gc.setFill(Color.WHITE);   // 如果不加，颜色会随着node颜色的更新而更新
        gc.fillText(String.format("%02d", (this.getTime() / 60)) + ":" + String.format("%02d", (this.getTime() % 60)), SContants.WIDTH/2 + 100, 50);

    }

    public void run() {

        try {
            while (timeCounter > 0) {
                timeCounter--;
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }

    public Thread getT() {
        return t;
    }

    public int getTimeCounter() {
        return timeCounter;
    }

    public void setT(Thread t) {
        this.t = t;
    }

    public void setTimeCounter(int timeCounter) {
        this.timeCounter = timeCounter;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public int getTime() {
        return timeCounter;
    }
}