package view;

import javax.swing.*;
import java.awt.*;

import client.ClientCommandsSenderThread;
import client.Config;

import static main.Config.*;

/**
 * Created by Oem on 2017-06-03.
 */
public class NetFrame extends JFrame
{
    private JFormattedTextField serverIpTextField;
    private JFormattedTextField ownIpTextField;
    private JFormattedTextField portTextField;
    private JComboBox playersComboBox;

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

        c.gridy = it++;
        JLabel playerLabel = new JLabel("Player");
        container.add(playerLabel, c);

        c.gridy = it++;
        playersComboBox = new JComboBox(PLAYER_NAMES);
        container.add(playersComboBox, c);

        c.gridx = 0;
        c.gridy = it++;
        JLabel ipLabel = new JLabel("Server IP Address");
        container.add(ipLabel, c);

        c.gridy = it++;
        serverIpTextField = new JFormattedTextField(Config.SERVER_IP);
        serverIpTextField.setColumns(20);
        container.add(serverIpTextField, c);

        c.gridy = it++;
        JLabel ownIpLabel = new JLabel("Your IP Address");
        container.add(ownIpLabel, c);

        c.gridy = it++;
        ownIpTextField = new JFormattedTextField(Config.OWN_IP);
        ownIpTextField.setColumns(20);
        container.add(ownIpTextField, c);

        c.gridy = it++;
        JLabel portLabel = new JLabel("Port");
        container.add(portLabel, c);

        c.gridy = it++;
        portTextField = new JFormattedTextField(Config.SERVER_PORT);
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
            Config.SERVER_IP = serverIpTextField.getText();
            Config.OWN_IP = ownIpTextField.getText();
            Config.SERVER_PORT = (int) portTextField.getValue();
            Config.SELECTED_PLAYER = playersComboBox.getSelectedIndex();

            new Thread(new ClientCommandsSenderThread()).start();

            dispose();
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Wrong input");
        }
    }
}
