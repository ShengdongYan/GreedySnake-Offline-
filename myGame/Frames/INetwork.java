package myGame.Frames;

import javafx.scene.effect.Light;

import java.util.LinkedList;

public interface INetwork {
    void sendSnakebodyA(); //把蛇的更新后的Linkedlist<Point> 身子发出去，
    void sendSnakeHeadA(); // 把蛇头的新的坐标发出去 Point  (x,y)

    void sendSnakebodyB(); //把蛇的更新后的Linkedlist<Point> 身子发出去，
    void sendSnakeHeadB(); // 把蛇头的新的坐标发出去 Point

    Light.Point reHeadofSnakeA(); // 收回蛇的头信息；(x,y)
    LinkedList<Light.Point> reBodyOfSnakeA(); //收回蛇的身子的信息（x，y）

    Light.Point reHeadofSnakeB(); // 收回蛇的头信息；(x,y)
    LinkedList<Light.Point> reBodyOfSnakeB(); // 收回蛇的身子的信息（x，y）

    void sendDirectionA();  //把方向发出去
    void sendFirectionB();

    Contants.DIRECTIONS reDirectionA();   //收新的方向
    Contants.DIRECTIONS reDirectionB();

    void sendWinner(); // 把胜利者发出去，数据库胜场加一；
}
