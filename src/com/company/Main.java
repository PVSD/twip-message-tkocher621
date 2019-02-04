package com.company;
import java.util.*;

public class Main {

    public static Scanner scan = new Scanner(System.in);

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

    public static void main(String[] args) {

        System.out.println("Welcome to jMessage.\n\n1. Start a new conversation\n2. View current conversations");

        String in = scan.nextLine();

        if (!TryParse(in))
        {

        }

    }
}
