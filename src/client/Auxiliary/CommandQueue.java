package client.Auxiliary;

import java.util.LinkedList;
import java.util.List;

public class CommandQueue {

    private List<Command> list;

    public CommandQueue()
    {
        list = new LinkedList<>();
    }

    public synchronized void push(Command com)
    {
        list.add(com);
    }

    public synchronized byte pop()
    {
        byte result = 0;
        if (!list.isEmpty())
        {
            result = list.get(0).command;
            list.remove(0);
        }

        return result;
    }

    public synchronized boolean isEmpty()
    {
        return list.isEmpty();
    }
}