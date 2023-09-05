package com.example.lotto.service;

import java.util.Arrays;
import java.util.List;

/*
* 당첨 로또 번호를 제공한다.
*/
public class WinningLottoProvider {
    public List<Integer> getWinningNumbers(String winningNumbersString) {
        String[] split = winningNumbersString.split(", ");

        return Arrays.stream(split)
            .map(Integer::parseInt)
            .toList();
    }
}
