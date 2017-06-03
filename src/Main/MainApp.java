package Main;

import Client.ClientOrdersSenderThread;

public class MainApp {

    public static void main (String args[])
    {
        new Thread(ClientOrdersSenderThread.getInstance()).start();
    }
}
