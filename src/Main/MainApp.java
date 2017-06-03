package Main;

import client.ClientOrdersSenderThread;

public class MainApp {

    public static void main (String args[])
    {
        new Thread(ClientOrdersSenderThread.getInstance()).start();
    }
}
