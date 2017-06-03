package view;

import javax.swing.*;

import java.awt.*;

import static main.Config.*;

public class View extends JFrame {

    private static class SingletonHelper {
        private static final View instance = new View();
    }

    MapPanel map;

    public static View getInstance()
    {
        return SingletonHelper.instance;
    }

    private View()
    {
        super();
        map = new MapPanel(this);
        initGUI();
    }

    private void initGUI()
    {
        setTitle(TITLE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(map);
        pack();
        setResizable(false);
        setVisible(true);
    }

    public void updateMap(byte[][] viewModel)
    {
        map.update(viewModel);
    }
}
