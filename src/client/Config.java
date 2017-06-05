package client;

public class Config
{
    private Config(){}

    // Server and own IP Addresses (assigned value is initial for net frame's text box)
    public static String SERVER_IP = "127.0.0.1";
    public static String OWN_IP = "127.0.0.1";

    // Server port (assigned value is initial for net frame's text box)
    public static int SERVER_PORT = 8888;

    // Buffer size
    static int BUFFER_SIZE_UDP = 1024;

    // Default disconnect command byte value
    public final static byte DISCONNECT_COMMAND = (byte) 255;

    // Default server message for player taken refusal
    final static String PLAYER_TAKEN_MSG = "PN";

    // Player character selection (assigned value is initial for net frame's text box)
    public static int SELECTED_PLAYER = 0;
}
