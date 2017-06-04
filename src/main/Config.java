package main;

/**
 * Created by Oem on 2017-06-03.
 */
public class Config {

    private Config(){}

    public final static String TITLE = "PeaR Bomberman - Early Access";
    public final static int FRAME_WIDTH = 1350;
    public final static int FRAME_HEIGHT = 850;

    public final static String NET_FRAME_TITLE = "Please insert data";
    public final static int NET_FRAME_WIDTH = 300;
    public final static int NET_FRAME_HEIGHT = 200;

    public final static int COLS = 20;
    public final static int ROWS = 15;

    public final static int TILE_SIZE = 50;
    public final static int TOP_PADDING = 50;
    public final static int LEFT_PADDING = 300;

    public final static int BLOCKS_TYPE_COUNT = 11;

    public final static byte CLEAR = 0;
    public final static byte BOMB = 1;
    public final static byte FLAME = 2;
    public final static byte WOOD = 3;
    public final static byte BRICK = 4;
    public final static byte BOMBERMAN = 5;
    public final static byte BOMBERMAN_ON_BOMB = 6;
    public final static byte EXTRA_BOMB = 7;
    public final static byte WOOD_WITH_EXTRA_BOMB = 8;
    public final static byte EXTRA_GUNPOWDER = 9;
    public final static byte WOOD_WITH_EXTRA_GUNPOWDER = 10;

    public final static byte MOVE_UP = 1;
    public final static byte MOVE_DOWN = 2;
    public final static byte MOVE_LEFT = 3;
    public final static byte MOVE_RIGHT = 4;
    public final static byte PLANT_BOMB = 5;
}
