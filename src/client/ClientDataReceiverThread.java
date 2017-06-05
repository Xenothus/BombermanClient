package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import view.MainFrame;
import static client.Config.*;
import static main.Config.*;

public class ClientDataReceiverThread implements Runnable {

    private final ConnectionInfo connectionInfo = ConnectionInfo.getInstance();
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
            InetAddress ip = InetAddress.getByName(SERVER_IP);
            byte[] buffer = new byte[BUFFER_SIZE_UDP];

            while (connectionInfo.isConnected())
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

                //Mozna odciazyc przesyl wstawiajac mape do jakiejs kolejki
                //z ktorej bedzie czytac wykonywacz update mapss
                MainFrame.getInstance().updateMap(array2D);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
