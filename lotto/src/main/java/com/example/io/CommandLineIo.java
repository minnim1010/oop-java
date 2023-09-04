package com.example.io;

import java.util.Scanner;

public class CommandLineIo {

    private CommandLineIo(){}

    private static final Scanner scanner = new Scanner(System.in);

    public static String input(){
        return scanner.nextLine();
    }

    public static void output(String message){
        System.out.println(message);
    }
}
