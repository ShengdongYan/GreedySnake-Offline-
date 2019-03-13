package myGame.Frames;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class TimeCounter implements Runnable{

    public boolean flag;
    private Thread t;
    private int time;
    private  boolean visible;


    public TimeCounter(int times) {
        flag = true;
        this.time = times;
    }

    public  void draw(GraphicsContext gc ){

        gc.setFont(Font.font(null, FontWeight.BLACK, 60));
       // gc.setFill(Color.WHITE);    如果不加，颜色可以随着node颜色的更新而更新
        gc.fillText(String.format("%02d", (this.getTime() / 60)) + ":" + String.format("%02d", (this.getTime() % 60)), Contants.WIDTH/2-80, 50);

    }

    public void run() {

            try {

                while (getTime() > 0) {
                    if(flag) {
                        time--;
                    }
                    Thread.sleep(1000);

                }
               if(getTime()<=0)
                   stopTimer();

            } catch (InterruptedException e) {
                // TODO: handle exception
            }
        }

        public void stopTimer(){
            flag = false;
        }

        public void startTimer(){
        flag = true;
        }

    public void start() {

            if (t == null) {
                t = new Thread(this);
                t.start();
            }

    }



    public Thread getT() {
        return t;
    }

    public int getTime() {
        return time;
    }

    public void setT(Thread t) {
        this.t = t;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }


}
