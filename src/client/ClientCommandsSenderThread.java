package client;

import view.MainFrame;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Objects;

import static client.Config.*;

public class ClientCommandsSenderThread implements Runnable {

    private final CommandQueue commands = CommandQueue.getInstance();
    private final ConnectionInfo connectionInfo = ConnectionInfo.getInstance();
    private int portUDP;

    private boolean isConnected;

    public ClientCommandsSenderThread()
    {
        isConnected = true;
    }

    @Override
    public void run()
    {
        connectWithServer();
        new Thread(new ClientDataReceiverThread(portUDP + 1)).start();

        try (DatagramSocket socket = new DatagramSocket())
        {
            InetAddress ip = InetAddress.getByName(SERVER_IP);
            byte[] buffer = new byte[BUFFER_SIZE_UDP];

            while (isConnected)
            {
                if (commands.isEmpty())
                    continue;

                byte commandToSend = commands.pop();
                if (commandToSend == DISCONNECT_COMMAND)
                {
                    isConnected = false;
                    connectionInfo.disconnect();
                }

                //Sending command to server
                buffer[0] = commandToSend;
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

    private void connectWithServer()
    {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             DataInputStream in = new DataInputStream(
                     new BufferedInputStream(socket.getInputStream()));
             DataOutputStream out = new DataOutputStream(
                     new BufferedOutputStream(socket.getOutputStream())))
        {
            //Sending IP Address and player character selection to server
            out.writeUTF(OWN_IP + "-" + SELECTED_PLAYER);
            out.flush();

            //Receiving UDP port value for further UDP connection
            //or denial msg
            String received = in.readUTF();
            if (Objects.equals(received, PLAYER_TAKEN_MSG))
                throw new Exception("Player character taken");

            portUDP = Integer.parseInt(received);

            // Initialization of main view frame
            MainFrame.getInstance();
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Could not connect to server");
            System.exit(1);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(1);
        }
    }
}
