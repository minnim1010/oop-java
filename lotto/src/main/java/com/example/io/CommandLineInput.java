package com.example.io;

import java.util.Scanner;

public class CommandLineInput {
    private static final Scanner scanner = new Scanner(System.in);

    private CommandLineInput() {
    }

    public static String input() {
        return scanner.nextLine();
    }

}
