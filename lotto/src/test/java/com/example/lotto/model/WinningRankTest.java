package com.example.lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinningRankTest {

    @DisplayName("로또 순위가 주어지면 로또 순위 결과를 업데이트한다.")
    @Test
    void add() {
        //given
        WinningRank winningRank = new WinningRank();

        //when
        winningRank.increase(LottoRank.FOURTH);
        winningRank.increase(LottoRank.FOURTH);
        winningRank.increase(LottoRank.FIRST);

        //then
        assertThat(winningRank.getRank()).hasSize(5)
            .containsEntry(LottoRank.FIRST, 1)
            .containsEntry(LottoRank.FOURTH, 2);
    }

    @DisplayName("로또 순위가 주어지면 해당 로또 순위의 로또 개수를 반환한다.")
    @Test
    void get() {
        //given
        WinningRank winningRank = new WinningRank();

        winningRank.increase(LottoRank.FOURTH);
        winningRank.increase(LottoRank.FOURTH);

        //when then
        assertThat(winningRank.getCount(LottoRank.FOURTH))
            .isEqualTo(2);
    }
}