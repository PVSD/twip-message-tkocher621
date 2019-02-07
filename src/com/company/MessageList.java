package com.company;
import java.util.*;

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
