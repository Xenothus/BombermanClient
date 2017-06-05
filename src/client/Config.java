package client;

/**
 * Created by Oem on 2017-06-03.
 */
public class Config
{
    private Config(){}

    public static String SERVER_IP = "127.0.0.1";
    public static String OWN_IP = "127.0.0.1";
    public static int SERVER_PORT = 8888;
    static int BUFFER_SIZE_UDP = 1024;

    public static int SELECTED_PLAYER = 0;

    public static String PLAYER_TAKEN_MSG = "PN";

    public static byte DISCONNECT_COMMAND = (byte) 255;
}
