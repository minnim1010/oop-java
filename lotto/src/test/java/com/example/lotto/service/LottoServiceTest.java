package com.example.lotto.service;

import com.example.lotto.constants.LottoConstants;
import com.example.lotto.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {

    private final LottoService lottoService = new LottoService();

    @Nested
    @DisplayName("자동, 수동 로또를 생성할 때 ")
    class getLottos {
        @DisplayName("성공 시 생성된 로또들을 반환한다.")
        @Test
        void successReturnCreatedLottos() {
            //given
            LottoCount totalLottoCnt = new LottoCount(103);
            List<Lotto> manualLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(4, 5, 6, 7, 8, 9)),
                new Lotto(List.of(10, 11, 12, 13, 14, 15))
            );
            LottoCount manualLottoCnt = new LottoCount(3);

            //when
            PurchasedLottos lottos = lottoService.getLottos(
                totalLottoCnt, manualLottoCnt, manualLottos);

            //then
            assertThat(lottos.getLottos()).hasSize(totalLottoCnt.getLottoCount())
                .allSatisfy(lotto -> assertThat(lotto.getNumbers()).hasSize(LottoConstants.NUMBERS_SIZE)
                    .allSatisfy(number -> assertThat(number)
                        .isBetween(LottoConstants.MIN_NUMBER, LottoConstants.MAX_NUMBER)));
            assertThat(lottos.getLottos().stream().limit(manualLottoCnt.getLottoCount()).toList())
                .containsExactly(
                    new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                    new Lotto(List.of(4, 5, 6, 7, 8, 9)),
                    new Lotto(List.of(10, 11, 12, 13, 14, 15))
                );
        }
    }

    @Nested
    @DisplayName("로또 당첨 통계를 계산할 때 ")
    class calculateStatistics {
        @DisplayName("성공 시 계산된 로또 당첨 통계를 반환한다.")
        @Test
        void successReturnLottoStatistics() {
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
            PurchaseAmount amount = new PurchaseAmount(1000000);

            //when
            LottoStatistics lottoStatistics = lottoService.calculateStatistics(
                winningLottoTicket, lottos, amount);

            //then
            assertThat(lottoStatistics.getWinningRank().getRank())
                .containsEntry(LottoRank.FIRST, 0)
                .containsEntry(LottoRank.SECOND, 1)
                .containsEntry(LottoRank.THIRD, 0)
                .containsEntry(LottoRank.FOURTH, 1)
                .containsEntry(LottoRank.FIFTH, 1);
            assertThat(lottoStatistics.getProfitRate()).isEqualTo(30.055);
        }
    }

}