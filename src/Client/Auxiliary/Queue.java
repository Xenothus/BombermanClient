package Client.Auxiliary;

import java.util.LinkedList;
import java.util.List;

public class Queue {

    private List<String> list;

    public Queue()
    {
        list = new LinkedList<>();
    }

    public void push(String val)
    {
        list.add(val);
    }

    public String pop()
    {
        String result = "";
        if (!list.isEmpty())
        {
            result = list.get(0);
            list.remove(0);
        }

        return result;
    }

    public String getFirst()
    {
        if (!list.isEmpty())
            return list.get(0);

        return "";
    }

    public boolean isEmpty()
    {
        return list.isEmpty();
    }
}
