package com.company;
import java.util.*;

public class MessageList {

    private List<Message> mList = new ArrayList<>();

    public void AddMessage(Message m)
    {
        mList.add(0, m);
    }

    public void RemoveMessage(Message m)
    {
        mList.remove(m);
    }

    public void DisplayMessage(Message m)
    {
        System.out.println(m.pMessage);
    }

    public void MoveMessage(Message m, int indx)
    {

    }

    public void ClearMessages()
    {
        mList.clear();
    }

}
