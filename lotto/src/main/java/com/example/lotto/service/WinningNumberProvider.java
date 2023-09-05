package com.example.lotto.service;

import java.util.Arrays;
import java.util.List;

public class WinningNumberProvider {
    public List<Integer> getWinningNumbers(String winningNumbersString) {
        String[] split = winningNumbersString.split(", ");

        return Arrays.stream(split)
            .map(Integer::parseInt)
            .toList();
    }
}
