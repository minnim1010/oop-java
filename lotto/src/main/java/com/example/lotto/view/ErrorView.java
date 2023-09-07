package com.example.lotto.view;

import com.example.io.CommandLineOutput;

public class ErrorView {

    public void showIllegalArgumentException(IllegalArgumentException ex) {
        CommandLineOutput.output(ex.getMessage());
        ex.printStackTrace();
    }

}
