package client.Auxiliary;

import java.util.LinkedList;
import java.util.List;

public class CommandQueue {

    private List<Command> list;

    public CommandQueue()
    {
        list = new LinkedList<>();
    }

    public void push(Command com)
    {
        list.add(com);
    }

    public byte pop()
    {
        byte result = 0;
        if (!list.isEmpty())
        {
            result = list.get(0).command;
            list.remove(0);
        }

        return result;
    }

    public boolean isEmpty()
    {
        return list.isEmpty();
    }
}