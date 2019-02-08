package com.company;
import java.util.*;

public class Main {

    // I didn't add exception handling btw, don't be stupid when using this and try entering asparagus for a conversation number
    public static Scanner scan = new Scanner(System.in);
    private static List<MessageList> conversations = new ArrayList<>();
    private static Random rand = new Random();

    public static void Menu()
    {
        System.out.println("[Welcome to jMessage]\n\n1. Start a new conversation\n2. View conversations");
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
        System.out.println("[" + mConvo.pNum + "]\n-exit: exit conversation\n-clear: clear conversation\n-remove: remove a message\n--------------------------------------------");
        conversations.add(mConvo);
        StartConversation(mConvo);
    }

    public static void RefreshChat(MessageList chat)
    {
        System.out.println("[" + chat.pNum + "]\n-exit: exit conversation\n-clear: clear conversation\n-remove: remove a message\n--------------------------------------------");
        chat.DisplayMessages();
    }

    public static void StartConversation(MessageList mConvo)
    {
        String in2 = null;
        do
        {
            in2 = scan.nextLine();
            if (!in2.equalsIgnoreCase("-exit") && in2.length() > 0)
            {
                if (in2.equalsIgnoreCase("-clear"))
                {
                    mConvo.ClearMessages();
                    System.out.println("Conversation cleared.");
                    RefreshChat(mConvo);
                    StartConversation(mConvo);
                    return;
                }
                else if (in2.equalsIgnoreCase("-remove"))
                {
                    RemoveMessage(mConvo);
                    System.out.println("Message removed.");
                    return;
                }
                else if (in2.equalsIgnoreCase("-move"))
                {
                    MoveMessage(mConvo);
                    System.out.println("Message moved.");
                    RefreshChat(mConvo);
                    StartConversation(mConvo);
                    return;
                }

                mConvo.AddMessage(new Message(mConvo.pNum, in2));

                if (rand.nextDouble() > 0.5)
                {
                    mConvo.AddMessage(new Message("you", "*really cool response*"));
                }
                RefreshChat(mConvo);
            }
        } while (!in2.equalsIgnoreCase("-exit"));
        Menu();
    }

    public static void ListConversations()
    {
        if (conversations.size() > 0)
        {
            System.out.println("Select a conversation to open. Add a - in front of the number to delete the conversation.");
            for (int i = 0; i < conversations.size(); i++)
            {
                System.out.println((i + 1) + ". " + conversations.get(i).pNum);
            }
            String ind = scan.nextLine();
            ind = scan.nextLine(); // WTJ
            int b = Integer.parseInt(ind.replace("-", ""));
            if (ind.contains("-"))
            {
                RemoveConversation(b - 1);
            }
            else if (b > 0 && b <= conversations.size())
            {
                ResumeConversation(b - 1);
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
        System.out.println("[" + convo.pNum + "]\n-exit: exit conversation\n-clear: clear conversation\n-remove: remove a message\n--------------------------------------------");
        convo.DisplayMessages();
        StartConversation(convo);
    }

    public static void RemoveConversation(int index)
    {
        System.out.println("Conversation with " + conversations.get(index).pNum + " deleted.");
        conversations.remove(index);
        Menu();
    }

    public static void RemoveMessage(MessageList chat)
    {
        if (chat.mList.size() > 0)
        {
            System.out.println("Select a message to remove.");
            HashMap<Integer, Message> temp = new HashMap<>();
            int b = 1;
            for (int i = 0; i < chat.mList.size(); i++)
            {
                Message m = chat.mList.get(i);
                if (!m.pNumber.equalsIgnoreCase("you"))
                {
                    System.out.println(b + ". " + m.GetMessage());
                    temp.put(b, m);
                    b++;
                }
            }
            int index = scan.nextInt();
            chat.RemoveMessage(temp.get(index));
        }
        else
        {
            System.out.println("You have not sent any messages.");
        }
        RefreshChat(chat);
        StartConversation(chat);
    }

    public static void MoveMessage(MessageList from)
    {
        System.out.println("Select a message to move.");
        for (int i = 0; i < from.mList.size(); i++)
        {
            Message m = from.mList.get(i);
            System.out.println((i + 1) + ". " + m.GetMessage());
        }
        int mIndex = scan.nextInt();

        System.out.println("Select a conversation to move message to.");
        HashMap<Integer, MessageList> temp = new HashMap<>();
        int b = 1;
        for (int i = 0; i < conversations.size(); i++)
        {
            MessageList m = conversations.get(i);
            if (m.pNum != from.pNum)
            {
                System.out.println(b + ". " + m.pNum);
                temp.put(b, m);
                b++;
            }
        }
        int cIndex = scan.nextInt();

        Message m = from.mList.get(mIndex - 1);
        from.mList.remove(m);
        temp.get(cIndex).AddMessage(m);
    }

    public static void main(String[] args)
    {
        Menu();
    }
}
