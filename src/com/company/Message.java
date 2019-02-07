package com.company;
import java.util.*;

public class Message {

    String pNumber;
    String pMessage;
    long pTime;

    public Message(String pNumber, String pMessage)
    {
        this.pNumber = pNumber;
        this.pMessage = pMessage;
        this.pTime = GetTime();
    }

    public long GetTime()
    {
        Date d = new Date();
        String time = d.toString();
        return Long.parseLong(time.substring(11, 13) + time.substring(14, 16) + time.substring(17, 19));
    }

}
