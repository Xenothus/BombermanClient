package view;

import javax.swing.*;
import java.awt.event.ActionEvent;

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
        //TODO wypada zamienic przesylanie na jedynie bajtowe - nawet lepiej bo latwiej i szybciej
        //ClientOrdersSenderThread.getInstance().sendToServer(command);
    }
}
