package myGame.Objects;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import myGame.Frames.Contants;

public class Snake {
  // public  LinkedList<Light.Point> list = new LinkedList<Light.Point>();
  protected enum DIRECTIONS {

      DIR_UP, DIR_DOWN, DIR_LEFT, DIR_RIGHT, DIR_LEFTUP, DIR_LEFTDOWN, DIR_RIGHTUP, DIR_RIGHTDOWN
  };
    private double x;
    private double y;
    private  double height;
    private  double width;
    private   DIRECTIONS direction ;
    private  boolean visible;
    private  boolean updated;
    private int length;
    private Color color;
    private Node node;
    private SnakeBody snakeBody;
    public Snake(double x, double y,Node node) {
        this.x = x;
        this.y = y;
        this.height = Contants.UnitHeight;
        this.width = Contants.UnitWidth;
        this.setDirection(DIRECTIONS.DIR_RIGHT);
        this.length = Contants.DEFAULT_LENGTH;
        this.node = node;

    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
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

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public boolean isUpdated() {
        return updated;
    }

    public DIRECTIONS getDirection() {
        return direction;
    }

    public void setDirection(DIRECTIONS direction) {
        this.direction = direction;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return this.color;
    }


    public void moveX(double x) {
        this.setX(getX() + x);
    }

    public void moveY(double y) {
        this.setY(getY() + y);
    }


    public  void  drawSnake(GraphicsContext gc){


        gc.setFill(getColor());
        gc.fillOval(getX(),getY(),getWidth(),getHeight());

        if (this.getDirection() == DIRECTIONS.DIR_DOWN || this.getDirection() == DIRECTIONS.DIR_UP) {
            gc.setFill(Color.web("#fff"));
            gc.fillOval(getX() - 2,            getY() + 3, getWidth() / 2, getHeight() / 2);
            gc.fillOval(getX() + getWidth()/2, getY() + 3, getWidth() / 2, getHeight() / 2);

            gc.setFill(Color.web("#333"));
            gc.fillOval(getX() - 2 +2,            getY() + 3 +2, getWidth() / 3, getHeight() / 3);
            gc.fillOval(getX() + getWidth()/2 +2, getY() + 3 +2, getWidth() / 3, getHeight() / 3);
        } else if (this.getDirection() == DIRECTIONS.DIR_LEFT || this.getDirection() == DIRECTIONS.DIR_RIGHT) {
            gc.setFill(Color.web("#fff"));
            gc.fillOval(getX() + 3, getY() -1,             getWidth() / 2, getHeight() / 2);
            gc.fillOval(getX() + 3, getY() + getWidth()/2, getWidth() / 2, getHeight() / 2);

            gc.setFill(Color.web("#333"));
            gc.fillOval(getX() + 3 + 2, getY() +2, 				   getWidth() / 3, getHeight() / 3);
            gc.fillOval(getX() + 3 + 2, getY() + getWidth()/2 + 2, getWidth() / 3, getHeight() / 3);
        } else if (this.getDirection() == DIRECTIONS.DIR_LEFTUP || this.getDirection() == DIRECTIONS.DIR_RIGHTDOWN) {
            gc.setFill(Color.web("#fff"));
            gc.fillOval(getX() + 2,                getY() + 1.1 * getWidth()/2,                getWidth() / 2, getHeight() / 2);
            gc.fillOval(getX() + 2 + getWidth()/2, getY() + 1.1 * getWidth()/2 - getWidth()/2, getWidth() / 2, getHeight() / 2);

            gc.setFill(Color.web("#333"));
            gc.fillOval(getX() + 2 + 2,                getY() + 1.1 * getWidth()/2 + 2, 			   getWidth() / 3, getHeight() / 3);
            gc.fillOval(getX() + 2 + getWidth()/2 + 2, getY() + 1.1 * getWidth()/2 - getWidth()/2 + 2, getWidth() / 3, getHeight() / 3);

        } else if (this.getDirection() == DIRECTIONS.DIR_LEFTDOWN || this.getDirection() == DIRECTIONS.DIR_RIGHTUP) {
            gc.setFill(Color.web("#fff"));
            gc.fillOval(getX(), getY(), getWidth() / 2, getHeight() / 2);
            gc.fillOval(getX() + getWidth() / 2, getY() + getWidth() / 2, getWidth() / 2, getHeight() / 2);

            gc.setFill(Color.web("#333"));
            gc.fillOval(getX() + 2, getY() + 2, getWidth() / 3, getHeight() / 3);
            gc.fillOval(getX() + getWidth() / 2 + 2, getY() + getWidth() / 2 + 2, getWidth() / 3, getHeight() / 3);

        }
    }

    public  void  update(){
      //  if(this.isReachBorder()){rebirth();}
        if (this.getDirection() == DIRECTIONS.DIR_UP) {
            moveY(-Contants.speed);
        } else if (this.getDirection() == DIRECTIONS.DIR_DOWN) {
            moveY(Contants.speed);
        } else if (this.getDirection() == DIRECTIONS.DIR_LEFT) {
            moveX(-Contants.speed);
        } else if (this.getDirection() == DIRECTIONS.DIR_RIGHT) {
            moveX(Contants.speed);
        } else if (this.getDirection() == DIRECTIONS.DIR_LEFTUP) {
            moveX(-Contants.speed * Math.sqrt(2)/2);
            moveY(-Contants.speed * Math.sqrt(2)/2);
        } else if (this.getDirection() == DIRECTIONS.DIR_LEFTDOWN) {
            moveX(-Contants.speed * Math.sqrt(2)/2);
            moveY(Contants.speed * Math.sqrt(2)/2);
        } else if (this.getDirection() == DIRECTIONS.DIR_RIGHTUP) {
            moveX(Contants.speed * Math.sqrt(2)/2);
            moveY(-Contants.speed * Math.sqrt(2)/2);
        } else if (this.getDirection() == DIRECTIONS.DIR_RIGHTDOWN) {
            moveX(Contants.speed * Math.sqrt(2)/2);
            moveY(Contants.speed * Math.sqrt(2)/2);
        }

    }

    public void onKeyPressed(KeyEvent event) {

        KeyCode tmpCode = event.getCode();
        // 如果反方向那么不处理，蛇不会掉头
        if ((tmpCode == KeyCode.UP && direction == DIRECTIONS.DIR_DOWN)
                || (tmpCode == KeyCode.DOWN && direction == DIRECTIONS.DIR_UP)
                || (tmpCode == KeyCode.RIGHT && direction == DIRECTIONS.DIR_LEFT)
                || (tmpCode == KeyCode.LEFT && direction == DIRECTIONS.DIR_RIGHT)
        ) {
            return;
        }

        updateDir(tmpCode, direction);
    }
    public void updateDir(KeyCode keyCode, DIRECTIONS dir) {

        if (keyCode == KeyCode.UP) {
            switch (dir) {
                case DIR_LEFTDOWN:
                    direction = DIRECTIONS.DIR_LEFT;
                    break;
                case DIR_LEFT:
                    direction = DIRECTIONS.DIR_LEFTUP;
                    break;
                case DIR_LEFTUP:
                    direction = DIRECTIONS.DIR_UP;
                    break;
                case DIR_RIGHTDOWN:
                    direction = DIRECTIONS.DIR_RIGHT;
                    break;
                case DIR_RIGHT:
                    direction = DIRECTIONS.DIR_RIGHTUP;
                    break;
                case DIR_RIGHTUP:
                    direction = DIRECTIONS.DIR_UP;
                    break;
                default:
                    break;
            }
        } else if (keyCode == KeyCode.DOWN) {
            switch (dir) {
                case DIR_LEFTDOWN:
                    direction = DIRECTIONS.DIR_DOWN;
                    break;
                case DIR_LEFT:
                    direction = DIRECTIONS.DIR_LEFTDOWN;
                    break;
                case DIR_LEFTUP:
                    direction = DIRECTIONS.DIR_LEFT;
                    break;
                case DIR_RIGHTDOWN:
                    direction = DIRECTIONS.DIR_DOWN;
                    break;
                case DIR_RIGHT:
                    direction = DIRECTIONS.DIR_RIGHTDOWN;
                    break;
                case DIR_RIGHTUP:
                    direction = DIRECTIONS.DIR_RIGHT;
                    break;
                default:
                    break;
            }
        } else if (keyCode == KeyCode.LEFT) {
            switch (dir) {
                case DIR_LEFTUP:
                    direction = DIRECTIONS.DIR_LEFT;
                    break;
                case DIR_LEFTDOWN:
                    direction = DIRECTIONS.DIR_LEFT;
                    break;
                case DIR_UP:
                    direction = DIRECTIONS.DIR_LEFTUP;
                    break;
                case DIR_DOWN:
                    direction = DIRECTIONS.DIR_LEFTDOWN;
                    break;
                case DIR_RIGHTUP:
                    direction = DIRECTIONS.DIR_UP;
                    break;
                case DIR_RIGHTDOWN:
                    direction = DIRECTIONS.DIR_DOWN;
                    break;
                default:
                    break;
            }
        } else if (keyCode == KeyCode.RIGHT) {
            switch (dir) {
                case DIR_LEFTUP:
                    direction = DIRECTIONS.DIR_UP;
                    break;
                case DIR_LEFTDOWN:
                    direction = DIRECTIONS.DIR_DOWN;
                    break;
                case DIR_UP:
                    direction = DIRECTIONS.DIR_RIGHTUP;
                    break;
                case DIR_DOWN:
                    direction = DIRECTIONS.DIR_RIGHTDOWN;
                    break;
                case DIR_RIGHTUP:
                    direction = DIRECTIONS.DIR_RIGHT;
                    break;
                case DIR_RIGHTDOWN:
                    direction = DIRECTIONS.DIR_RIGHT;
                    break;
                default:
                    break;
            }
        }
    }
     public void rebirth(){
        this.setX(Contants.userAX);
        this.setY(Contants.userAY);
        this.setLength(Contants.DEFAULT_LENGTH);
        this.setDirection(DIRECTIONS.DIR_UP);

    }
    public boolean isReachBorder(){
        if (getX()+getWidth()>=Contants.WIDTH||getY()+getHeight()>=Contants.HEIGHT||getX()<0||getY()<0){
            rebirth();
            return true;
        }
        else return false;
    }

    public boolean isGetNode(){
        if(getX() + getWidth() > node.getX() && getX() < node.getX() + node.getWidth()
                && getY() + getHeight()  > node.getY() && getY() < node.getY() + node.getHeight()){
            this.setLength(getLength()+1);
            return true;
        }

        return false;
    }


/*    public boolean isCollisionWith(SObject baseObject) {
        if (getX() + getWidth() - bias> baseObject.getX() && getX() < baseObject.getX() + baseObject.getWidth() - bias
                && getY() + getHeight() - bias > baseObject.getY() && getY() < baseObject.getY() + baseObject.getHeight() - bias) {
            return true;
        }
        return false;
    }*/

}
