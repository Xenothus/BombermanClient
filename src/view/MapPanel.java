package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static main.Config.*;

/**
 * Created by Adam on 2017-04-04.
 */
public class MapPanel extends JPanel
{
    BufferedImage backgroundImage, foregroundLeavesImage, worldObjects[];
    BufferedImage[][] tiles;
    MainFrame view;

    public MapPanel(MainFrame view)
    {
        super();
        this.view=view;
        tiles=new BufferedImage[COLS][ROWS];
        worldObjects=new BufferedImage[6];
        loadImages();
        setControls();
    }

    void setControls()
    {
        getInputMap().put(KeyStroke.getKeyStroke("A"),
                "Left");
        getInputMap().put(KeyStroke.getKeyStroke("D"),
                "Right");
        getInputMap().put(KeyStroke.getKeyStroke("W"),
                "Up");
        getInputMap().put(KeyStroke.getKeyStroke("S"),
                "Down");
        getInputMap().put(KeyStroke.getKeyStroke("B"),
                "Plant");

        getActionMap().put("Left", new PlayerAction(MOVE_LEFT));
        getActionMap().put("Right", new PlayerAction(MOVE_RIGHT));
        getActionMap().put("Up", new PlayerAction(MOVE_UP));
        getActionMap().put("Down", new PlayerAction(MOVE_DOWN));
        getActionMap().put("Plant", new PlayerAction(PLANT_BOMB));
    }

    private void loadImages()
    {
        try {
            backgroundImage = ImageIO.read(new File("Grafika\\tloPlanszy.png"));
            foregroundLeavesImage = ImageIO.read(new File("Grafika\\ramkaPlanszy.png"));
            worldObjects[CLEAR] = null;
            worldObjects[BOMB] = ImageIO.read(new File("Grafika\\bomba.png"));
            worldObjects[FLAME] = ImageIO.read(new File("Grafika\\wybuch.png"));
            worldObjects[BOMBERMAN] = ImageIO.read(new File("Grafika\\postac.png"));
            worldObjects[WOOD] = ImageIO.read(new File("Grafika\\plot.png"));
            worldObjects[BRICK] = ImageIO.read(new File("Grafika\\mur.png"));

        }
        catch (IOException e){System.out.print(e.getMessage());}
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(backgroundImage,0,0,null);
        for(int i=0;i<COLS;i++)
            for(int j=0;j<ROWS;j++)
                if(tiles[i][j]!=null)
                    g.drawImage(tiles[i][j],i*TILE_SIZE+LEFT_PADDING,j*TILE_SIZE+TOP_PADDING,null);
        g.drawImage(foregroundLeavesImage,0,0,null);
    }

    public void update(byte[][] viewModel)
    {
        for(int i=0; i<COLS; i++)
            for (int j=0; j<ROWS;j++) {
                BufferedImage tile=worldObjects[viewModel[i][j]];
                tiles[i][j] = tile;
            }
        repaint();
    }
}
