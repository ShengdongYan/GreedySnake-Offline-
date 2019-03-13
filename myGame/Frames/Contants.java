package myGame.Frames;


/**
 *
 * @author Shengdong Yan
 * @version 2019-03-07
 */
public class Contants {
	public enum GameState{READY, PAUSE, RUN, TIMEOUT};
	public enum DIRECTIONS {

		DIR_UP, DIR_DOWN, DIR_LEFT, DIR_RIGHT, DIR_LEFTUP, DIR_LEFTDOWN, DIR_RIGHTUP, DIR_RIGHTDOWN
	};

	/**
	 * 游戏屏幕大小
	 */
	public static final int WIDTH = 1000, HEIGHT = 800;
	/**
	 * 蛇的每一个点的大小
	 */
	public static final int UnitHeight = 22, UnitWidth = 22;
	/**
	 * 蛇的每一个点之间的距离
	 */
	public static final int DISTANCE = 8;
	/*
	* 重生地点
	* */
	public static final int userAX = WIDTH*3/4;
	public static final int userAY = HEIGHT-150;

	public static final int userBX = WIDTH/4;
	public static final int userBY = HEIGHT-150;

	public static final int DEFAULT_LENGTH = 6;

	public static final int GameDuration = 10; //   设置游戏时长

	public static final int duration = 100; // 刷新周期。

	public static final double speed=12; //蛇的速度

}
