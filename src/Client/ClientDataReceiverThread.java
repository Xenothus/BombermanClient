package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;

public class ClientDataReceiverThread implements Runnable {

    private final static String DEFAULT_SERVER_IP = "127.0.0.1";

    private final static int BUFFER_SIZE_UDP = 1024;

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
                String receivedMessage = new String(dps.getData(), 0, dps.getLength());
                System.out.println(receivedMessage);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
