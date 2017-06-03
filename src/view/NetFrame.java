package view;

import client.ClientCommandsSenderThread;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static main.Config.*;

/**
 * Created by Oem on 2017-06-03.
 */
public class NetFrame extends JFrame
{
    private JFormattedTextField ipTextField;
    private JFormattedTextField portTextField;

    public NetFrame()
    {
        super();
        initGUI();
    }

    private void initGUI()
    {
        setTitle(NET_FRAME_TITLE);
        setSize(NET_FRAME_WIDTH, NET_FRAME_HEIGHT);

        JPanel container = new JPanel(new GridBagLayout());
        container.setPreferredSize(new Dimension(NET_FRAME_WIDTH, NET_FRAME_HEIGHT));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        int it = 0;

        c.gridx = 0;
        c.gridy = it++;
        JLabel ipLabel = new JLabel("IP Address");
        container.add(ipLabel, c);

        c.gridy = it++;
        ipTextField = new JFormattedTextField();
        ipTextField.setColumns(20);
        container.add(ipTextField, c);

        c.gridy = it++;
        JLabel portLabel = new JLabel("Port");
        container.add(portLabel, c);

        c.gridy = it++;
        portTextField = new JFormattedTextField();
        portTextField.setColumns(20);
        container.add(portTextField, c);

        c.gridy = it++;
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> startGame());
        container.add(startButton, c);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(container);
        pack();
        setResizable(false);
        setVisible(true);
    }

    private void startGame()
    {
        try
        {
            String inputIp = ipTextField.getText();
            String portString = portTextField.getText();

            int port;
            port = Integer.parseInt(portString);

            MainFrame.getInstance();
            new Thread((ClientCommandsSenderThread.getInstance())).start();

            dispose();
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Wrong input");
        }
    }
}
