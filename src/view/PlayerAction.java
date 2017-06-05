package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import client.CommandQueue;

public class PlayerAction extends AbstractAction
{
    byte command;

    public PlayerAction(byte command)
    {
        super();
        this.command = command;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        CommandQueue.getInstance().push(command);
    }
}
