package com.example.lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoPurchaseResultTest {

    @DisplayName("로또 순위가 주어지면 로또 순위 결과를 업데이트한다.")
    @Test
    void add() {
        //given
        LottoPurchaseResult lottoPurchaseResult = new LottoPurchaseResult();

        //when
        lottoPurchaseResult.update(LottoRank.FOURTH);
        lottoPurchaseResult.update(LottoRank.FOURTH);
        lottoPurchaseResult.update(LottoRank.FIRST);

        //then
        assertThat(lottoPurchaseResult.getRank()).hasSize(5)
            .containsEntry(LottoRank.FIRST, 1)
            .containsEntry(LottoRank.FOURTH, 2);
    }

    @DisplayName("로또 순위가 주어지면 해당 로또 순위의 로또 개수를 반환한다.")
    @Test
    void get() {
        //given
        LottoPurchaseResult lottoPurchaseResult = new LottoPurchaseResult();

        lottoPurchaseResult.update(LottoRank.FOURTH);
        lottoPurchaseResult.update(LottoRank.FOURTH);

        //when then
        assertThat(lottoPurchaseResult.get(LottoRank.FOURTH))
            .isEqualTo(2);
    }
}