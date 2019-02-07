package com.company;
import com.sun.org.apache.xml.internal.security.Init;

import javax.xml.stream.events.StartDocument;
import java.util.*;

public class Main {

    public static Scanner scan = new Scanner(System.in);
    private static List<MessageList> conversations = new ArrayList<>();
    private static Random rand = new Random();

    public static boolean TryParse(String a)
    {
        try
        {
            Integer.parseInt(a);
        }
        catch(Exception x)
        {
            return false;
        }
        return true;
    }

    public static void Menu()
    {
        System.out.println("Welcome to jMessage.\n\n1. Start a new conversation\n2. View conversations");
        int in = scan.nextInt();

        switch (in)
        {
            case 1:
                InitConversation();
                break;
            case 2:
                ListConversations();
                break;
            default:
                System.out.println("Invalid option!");
                Menu();
                break;
        }
    }

    public static void InitConversation()
    {
        System.out.println("Enter a phone number");
        String in = scan.nextLine();
        in = scan.nextLine(); // WTJ
        MessageList mConvo = new MessageList(in);
        conversations.add(mConvo);
        StartConversation(mConvo);
    }

    public static void StartConversation(MessageList mConvo)
    {
        String in2 = null;
        do
        {
            in2 = scan.nextLine();
            if (!in2.equalsIgnoreCase("exit") && in2.length() > 0)
            {
                mConvo.AddMessage(new Message(mConvo.pNum, in2, System.currentTimeMillis()));
                if (rand.nextDouble() > 0.5)
                {
                    mConvo.AddMessage(new Message("you", "*really cool response*", System.currentTimeMillis()));
                    System.out.println("*really cool response*");
                }
            }
        } while (!in2.equalsIgnoreCase("exit"));
        Menu();
    }

    public static void ListConversations()
    {
        if (conversations.size() > 0)
        {
            System.out.println("Select a conversation.");
            for (int i = 0; i < conversations.size(); i++)
            {
                System.out.println((i + 1) + ". " + conversations.get(i).pNum);
            }
            int ind = scan.nextInt();
            if (ind > 0 && ind <= conversations.size())
            {
                ResumeConversation(ind - 1);
            }
            else
            {
                System.out.println("Invalid option!");
            }
        }
        else
        {
            System.out.println("You don't have any conversations.");
            Menu();
        }
    }

    public static void ResumeConversation(int index)
    {
        MessageList convo = conversations.get(index);
        System.out.println("Conversation with " + convo.pNum + ". Type exit to exit conversation.");
        for (Message m : convo.mList)
        {
            System.out.println(m.pMessage);
        }
        StartConversation(convo);
    }

    public static void main(String[] args)
    {
        Menu();
    }
}
