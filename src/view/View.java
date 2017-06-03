package view;

import javax.swing.*;

import static Main.Config.*;

public class View extends JFrame {

    MapPanel map;

    public View()
    {
        super();
        map = new MapPanel(this);
        initGUI();
    }

    private void initGUI()
    {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(map);
        pack();
    }

    public void updateMap(byte[][] viewModel)
    {
        map.update(viewModel);
    }
}
