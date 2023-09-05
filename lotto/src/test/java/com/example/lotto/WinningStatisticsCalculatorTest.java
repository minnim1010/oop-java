package com.example.lotto;

import com.example.lotto.service.WinningStatisticsCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WinningStatisticsCalculatorTest {

    private final List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
    private final WinningStatisticsCalculator winningStatisticsCalculator = new WinningStatisticsCalculator(winningNumbers);

    @DisplayName("여러 개의 로또와 당첨 로또를 비교해 일치하는 숫자의 개수와 상금을 계산한다.")
    @Test
    void calculate() {
        //given
        List<List<Integer>> lottos = new ArrayList<>(
            Arrays.asList(
                List.of(1, 3, 34, 9, 6, 45),
                List.of(3, 5, 7, 8, 2, 1),
                List.of(1, 2, 3, 4, 5, 9),
                List.of(1, 2, 3, 4, 5, 6)
            )
        );

        //when
        winningStatisticsCalculator.calculate(lottos);

        //then
        Map<Integer, Integer> matchCnt = winningStatisticsCalculator.getMatchCnt();
        assertThat(matchCnt).hasSize(4)
            .containsEntry(3, 1)
            .containsEntry(4, 1)
            .containsEntry(5, 1)
            .containsEntry(6, 1);

        assertThat(winningStatisticsCalculator.getReward()).isEqualTo(2001555000);
    }
}