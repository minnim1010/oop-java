package com.example.lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoStatisticsTest {

    @Nested
    @DisplayName("로또 통계를 생성할 때 ")
    class create {
        @DisplayName("성공 시 생성된 LottoStatistics를 반환한다.")
        @Test
        void successReturnNewLottoStatistics() {
            // given
            Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            int bonusBall = 9;
            WinningLottoTicket winningLottoTicket = new WinningLottoTicket(winningLotto, bonusBall);
            PurchasedLottos lottos = new PurchasedLottos(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 9)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12)),
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),
                new Lotto(List.of(3, 4, 5, 6, 7, 8))
            ));
            LottoWinningRankResult lottoWinningRankResult = LottoWinningRankResult.create(winningLottoTicket, lottos);

            PurchaseAmount amount = new PurchaseAmount(300_000);

            // when
            LottoStatistics lottoStatistics = LottoStatistics.create(lottoWinningRankResult, amount);

            // then
            assertThat(lottoStatistics.getLottoWinningRank()).isEqualTo(lottoWinningRankResult);
            assertThat(lottoStatistics.getReward()).isEqualTo(30_055_000);
            assertThat(lottoStatistics.getProfitRate()).isEqualTo(30055000 / 300000D);
        }

        @DisplayName("유효하지 않은 WinningRank가 주어지면 예외가 발생한다.")
        @Test
        void failWithInvalidWinningRank() {
            // given
            PurchaseAmount amount = new PurchaseAmount(300_000);

            // when, then
            assertThatThrownBy(() -> LottoStatistics.create(null, amount))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("유효하지 않은 PurchasedAmount가 주어지면 예외가 발생한다.")
        @Test
        void failWithInvalidProfitRate() {
            // given
            Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            int bonusBall = 9;
            WinningLottoTicket winningLottoTicket = new WinningLottoTicket(winningLotto, bonusBall);
            PurchasedLottos lottos = new PurchasedLottos(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 9)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12)),
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),
                new Lotto(List.of(3, 4, 5, 6, 7, 8))
            ));
            LottoWinningRankResult lottoWinningRankResult = LottoWinningRankResult.create(winningLottoTicket, lottos);

            // when, then
            assertThatThrownBy(() -> LottoStatistics.create(lottoWinningRankResult, null))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}