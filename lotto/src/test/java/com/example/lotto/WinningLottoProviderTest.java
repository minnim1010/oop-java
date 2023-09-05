package com.example.lotto;

import com.example.lotto.service.WinningLottoProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoProviderTest {

    private final WinningLottoProvider winningLottoProvider = new WinningLottoProvider();

    @DisplayName("당첨 번호를 반환한다.")
    @Test
    void getWinningNumbers(){
        //given
        String winningNumbersString = "23, 45, 2, 3, 4, 1";
        List<Integer> expected = List.of(23, 45, 2, 3, 4, 1);

        //when
        List<Integer> winningNumbers = winningLottoProvider.getWinningNumbers(winningNumbersString);

        //then
        assertThat(winningNumbers).isEqualTo(expected);
    }
}