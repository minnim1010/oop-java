package com.example.lotto.view;

import com.example.io.CommandLineOutput;

public class ErrorView {
    private static final String MSG_PREFIX = "[Error] ";

    public void showIllegalArgumentException(IllegalArgumentException ex) {
        CommandLineOutput.output(MSG_PREFIX + ex.getMessage());
        ex.printStackTrace();
    }
}
