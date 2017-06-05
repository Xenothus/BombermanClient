package view;

import javax.swing.*;
import java.awt.event.ActionEvent;

import client.ClientCommandsSenderThread;
import client.CommandQueue;

/**
 * Created by Adam on 2017-04-05.
 */
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
