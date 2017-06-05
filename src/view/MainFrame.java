package view;

import client.CommandQueue;

import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static main.Config.*;
import static client.Config.DISCONNECT_COMMAND;

public class MainFrame extends JFrame {

    private static class SingletonHelper {
        private static final MainFrame instance = new MainFrame();
    }

    private MapPanel map;

    public static MainFrame getInstance()
    {
        return SingletonHelper.instance;
    }

    private MainFrame()
    {
        super();
        map = new MapPanel();
        initGUI();
    }

    private void initGUI()
    {
        setTitle(TITLE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        add(map);
        pack();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                terminate();
            }
        });
    }

    public void updateMap(byte[][] viewModel)
    {
        map.update(viewModel);
    }

    private void terminate()
    {
        CommandQueue.getInstance().push(DISCONNECT_COMMAND);
        dispose();
    }
}
