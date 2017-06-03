package main;

import client.ClientCommandsSenderThread;
import view.MainFrame;
import view.NetFrame;

public class MainApp {

    public static void main (String args[])
    {
        new NetFrame();

        //MainFrame.getInstance();

        //new Thread(ClientCommandsSenderThread.getInstance()).start();

    }
}
