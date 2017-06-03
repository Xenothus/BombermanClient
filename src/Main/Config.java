package Main;

/**
 * Created by Oem on 2017-06-03.
 */
public class Config {

    private Config(){}

    public final static String TITLE = "PeaR Bomberman - Early Access";
    public final static int WIDTH = 1350;
    public final static int HEIGHT = 850;

    public final static int COLS = 20;
    public final static int ROWS = 15;

    public final static int TILE_SIZE = 50;
    public final static int TOP_PADDING = 50;
    public final static int LEFT_PADDING = 300;

    public final static byte CLEAR = 0;
    public final static byte BOMB = 1;
    public final static byte FLAME = 2;
    public final static byte WOOD = 3;
    public final static byte BRICK = 4;
    public final static byte BOMBERMAN = 5;

    public final static byte MOVE_UP = 1;
    public final static byte MOVE_DOWN = 2;
    public final static byte MOVE_LEFT = 3;
    public final static byte MOVE_RIGHT = 4;
    public final static byte PLANT_BOMB = 5;
}
