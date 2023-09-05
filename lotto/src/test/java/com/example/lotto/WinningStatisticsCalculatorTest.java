package com.example.lotto;

import com.example.lotto.domain.Lotto;
import com.example.lotto.service.WinningStatisticsCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WinningStatisticsCalculatorTest {

    private final Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    private final WinningStatisticsCalculator winningStatisticsCalculator =
        new WinningStatisticsCalculator(winningLotto);

    @DisplayName("여러 개의 로또와 당첨 로또를 비교해 일치하는 숫자의 개수와 상금을 계산한다.")
    @Test
    void calculate() {
        //given
        List<Lotto> lottos = new ArrayList<>(
            Arrays.asList(
                new Lotto(List.of(1, 3, 34, 9, 6, 45)),
                new Lotto(List.of(3, 5, 7, 8, 2, 1)),
                new Lotto(List.of(1, 2, 3, 4, 5, 9)),
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
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