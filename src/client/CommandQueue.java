package client;

import java.util.LinkedList;
import java.util.List;

public class CommandQueue {

    private static class SingletonHelper {
        private static final CommandQueue instance = new CommandQueue();
    }

    private List<Command> list;

    public static CommandQueue getInstance()
    {
        return SingletonHelper.instance;
    }

    private CommandQueue()
    {
        list = new LinkedList<>();
    }

    public synchronized void push(byte command)
    {
        list.add(new Command(command));
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


    private class Command
    {
        byte command;

        Command(byte command)
        {
            this.command = command;
        }
    }
}