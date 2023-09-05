package com.example.lotto.service;

import com.example.lotto.domain.Lotto;
import com.example.lotto.domain.LottoPurchaseResult;
import com.example.lotto.domain.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningStatisticsCalculatorTest {

    @DisplayName("여러 개의 로또와 당첨 로또를 비교해 일치하는 숫자의 개수와 상금을 계산한다.")
    @Test
    void calculate() {
        //given
        int amount = 6000;
        WinningStatisticsCalculator winningStatisticsCalculator =
            new WinningStatisticsCalculator(amount);

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusBall = 9;

        List<Lotto> lottos = new ArrayList<>(
            Arrays.asList(
                new Lotto(List.of(1, 3, 2, 9, 6, 45)),
                new Lotto(List.of(3, 5, 7, 8, 2, 1)),
                new Lotto(List.of(1, 2, 3, 4, 5, 9)),
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
            )
        );

        //when
        winningStatisticsCalculator.calculate(winningLotto, bonusBall, lottos);

        //then
        LottoPurchaseResult rankResult = winningStatisticsCalculator.getRankResult();
        assertThat(rankResult.get(LottoRank.FIRST)).isEqualTo(1);
        assertThat(rankResult.get(LottoRank.SECOND)).isEqualTo(1);
        assertThat(rankResult.get(LottoRank.FOURTH)).isEqualTo(2);

        assertThat(winningStatisticsCalculator.getReward()).isEqualTo(2030100000L);
    }
}