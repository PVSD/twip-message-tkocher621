package com.company;
import java.util.*;

class comp implements Comparator<Message>
{
    public int compare(Message m1, Message m2)
    {
        return (m1.pTime > m2.pTime) ? 1 : -1;
    }
}

public class MessageList {

    public List<Message> mList = new ArrayList<>();
    public String pNum;

    public MessageList(String pNum)
    {
        this.pNum = pNum;
    }

    public void AddMessage(Message m)
    {
        mList.add(0, m);
        Collections.sort(mList, new comp());
    }

    public void RemoveMessage(Message m)
    {
        mList.remove(m);
    }

    public void DisplayMessages()
    {
        for (Message m : mList)
        {
            System.out.println(m.GetMessage());
        }
    }

    public void ClearMessages()
    {
        mList.clear();
    }

}
