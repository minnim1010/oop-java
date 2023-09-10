package com.example.lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoWinningRankResultTest {

    @DisplayName("로또 당첨 순위를 계산한 결과를 반환한다.")
    @Test
    void calculate() {
        //given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusBall = 9;
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket(winningLotto, bonusBall);
        PurchasedLottos lottos = new PurchasedLottos(List.of(
            new Lotto(List.of(1, 2, 3, 4, 5, 9)),
            new Lotto(List.of(7, 8, 9, 10, 11, 12)),
            new Lotto(List.of(1, 2, 3, 8, 9, 10)),
            new Lotto(List.of(3, 4, 5, 6, 7, 8))
        ));

        //when
        LottoWinningRankResult result = LottoWinningRankResult.create(winningLottoTicket, lottos);

        //then
        assertThat(result.getWinningRankResult())
            .containsEntry(LottoRank.FIRST, 0)
            .containsEntry(LottoRank.SECOND, 1)
            .containsEntry(LottoRank.THIRD, 0)
            .containsEntry(LottoRank.FOURTH, 1)
            .containsEntry(LottoRank.FIFTH, 1);
    }
}