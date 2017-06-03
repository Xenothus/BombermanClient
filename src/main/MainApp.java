package main;

import client.ClientCommandsSenderThread;
import view.View;

public class MainApp {

    public static void main (String args[])
    {
        View.getInstance();

        new Thread(ClientCommandsSenderThread.getInstance()).start();

    }
}
