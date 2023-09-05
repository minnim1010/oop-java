package com.example;

import com.example.lotto.service.WinningNumberProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningNumberProviderTest {

    private final WinningNumberProvider winningNumberProvider = new WinningNumberProvider();

    @DisplayName("당첨 번호를 반환한다.")
    @Test
    void getWinningNumbers(){
        //given
        String winningNumbersString = "23, 45, 2, 3, 4, 1";
        List<Integer> expected = List.of(23, 45, 2, 3, 4, 1);

        //when
        List<Integer> winningNumbers = winningNumberProvider.getWinningNumbers(winningNumbersString);

        //then
        assertThat(winningNumbers).isEqualTo(expected);
    }
}