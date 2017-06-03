package client;

import view.MainFrame;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static client.Config.*;
import static main.Config.*;

public class ClientDataReceiverThread implements Runnable {

    private int portUDP;

    public ClientDataReceiverThread(int portUDP)
    {
        this.portUDP = portUDP;
    }

    @Override
    public void run()
    {
        try (DatagramSocket socket = new DatagramSocket(portUDP))
        {
            InetAddress ip = InetAddress.getByName(DEFAULT_SERVER_IP);
            byte[] buffer = new byte[BUFFER_SIZE_UDP];

            while (true)    //TODO (in distant future) end this loop properly
            {
                //Receiving data from server
                DatagramPacket dps = new DatagramPacket(buffer, BUFFER_SIZE_UDP);
                socket.receive(dps);

                byte[][] array2D = new byte[COLS][ROWS];
                int bufferIt = 0;
                for (int i = 0; i < COLS; i++)
                {
                    for (int k = 0; k < ROWS; k++)
                    {
                        array2D[i][k] = buffer[bufferIt];
                        bufferIt++;
                    }
                }

                MainFrame.getInstance().updateMap(array2D);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
