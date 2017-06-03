package main;

import client.ClientCommandsSenderThread;

public class MainApp {

    public static void main (String args[])
    {
        new Thread(ClientCommandsSenderThread.getInstance()).start();
    }
}
