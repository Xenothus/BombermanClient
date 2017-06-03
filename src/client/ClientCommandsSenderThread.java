package client;

import client.auxiliary.Command;
import client.auxiliary.CommandQueue;

import java.io.*;
import java.net.*;

import static client.Config.*;

public class ClientCommandsSenderThread implements Runnable {

    private static class SingletonHelper {
        private static final ClientCommandsSenderThread instance = new ClientCommandsSenderThread();
    }

    private CommandQueue commands;
    private int portUDP;

    private ClientCommandsSenderThread()
    {
        commands = new CommandQueue();

        //Testing
/*        orderQueue.push("New order");
        orderQueue.push("Hello");
        orderQueue.push("New hello");*/

//        byte one = 2;
//        commands.push(new Command(one));
//        commands.push(new Command(one));
//        commands.push(new Command(one));
        //test was successful
    }

    public static ClientCommandsSenderThread getInstance()
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
                if (commands.isEmpty())
                    continue;

                //Sending command to server
                buffer[0] = commands.pop();
                DatagramPacket dp = new DatagramPacket(
                        buffer, BUFFER_SIZE_UDP, ip, portUDP);
                socket.send(dp);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void sendToServer(byte command)
    {
        commands.push(new Command(command));
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
