package view;

import javax.swing.*;

import static main.Config.*;

public class MainFrame extends JFrame {

    private static class SingletonHelper {
        private static final MainFrame instance = new MainFrame();
    }

    MapPanel map;

    public static MainFrame getInstance()
    {
        return SingletonHelper.instance;
    }

    private MainFrame()
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
