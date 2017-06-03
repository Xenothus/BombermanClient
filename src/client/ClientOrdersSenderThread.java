package client;

import client.Auxiliary.Queue;

import java.io.*;
import java.net.*;

import static client.Config.*;

public class ClientOrdersSenderThread implements Runnable {

    private static class SingletonHelper {
        private static final ClientOrdersSenderThread instance = new ClientOrdersSenderThread();
    }

    private Queue orderQueue;
    private int portUDP;

    private ClientOrdersSenderThread()
    {
        orderQueue = new Queue();

        //Testing
        orderQueue.push("New order");
        orderQueue.push("Hello");
        orderQueue.push("New hello");
    }

    public static ClientOrdersSenderThread getInstance()
    {
        return SingletonHelper.instance;
    }

    @Override
    public void run()
    {
        connectWithServer();
        new Thread(new ClientDataReceiverThread(portUDP + 1)).start();

        try (DatagramSocket socket = new DatagramSocket())
        {
            InetAddress ip = InetAddress.getByName(DEFAULT_SERVER_IP);
            byte[] buffer = new byte[BUFFER_SIZE_UDP];

            while (true)    //TODO (in distant future) end this loop properly
            {
                if (orderQueue.isEmpty())
                    continue;

                //Sending order message to server
                String message = orderQueue.pop();
                DatagramPacket dp = new DatagramPacket(
                        message.getBytes(), message.length(), ip, portUDP);
                socket.send(dp);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void sendToServer(String msg)
    {
        orderQueue.push(msg);
    }

    private void connectWithServer()
    {
        try (Socket socket = new Socket(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
             DataInputStream in = new DataInputStream(
                     new BufferedInputStream(socket.getInputStream()));
             DataOutputStream out = new DataOutputStream(
                     new BufferedOutputStream(socket.getOutputStream())))
        {
            //Sending IP Address to server
            out.writeUTF("127.0.0.1");
            out.flush();

            //Receiving UDP port value for farther UDP connection
            portUDP = Integer.parseInt(in.readUTF());
            System.out.print(portUDP);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
