package com.example.lotto.service;

import com.example.lotto.constants.LottoConstants;
import com.example.lotto.domain.Lotto;
import com.example.lotto.domain.LottoRank;
import com.example.lotto.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoServiceTest {

    private final LottoService lottoService = new LottoService();

    @Nested
    @DisplayName("구매한 금액으로 산 로또 개수를 계산할 때 ")
    class getTotalPurchasedLottoCountConstants {
        @DisplayName("계산 성공 시 살 수 있는 최대 로또 개수를 반환한다.")
        @CsvSource(value = {"15000:15", "1000:1", "14400:14"}, delimiter = ':')
        @ParameterizedTest
        void successReturnPurchasedLottoCount(int amount, int expected) {
            //given
            //when
            TotalPurchasedLottoCount lottoCount = lottoService.getTotalPurchasedLottoCount(
                new PurchaseAmount(amount));

            //then
            assertThat(lottoCount.getLottoCount()).isEqualTo(expected);
        }

        @DisplayName("구매 금액이 1000 미만이라면 예외가 발생한다.")
        @Test
        void failWithPurchasedAmountLessOrEqual1000() {
            //given
            int amount = 999;

            //when then
            assertThatThrownBy(() -> lottoService.getTotalPurchasedLottoCount(
                new PurchaseAmount(amount)))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("주어진 로또 개수만큼 로또를 생성할 때 ")
    class getLottos {
        @DisplayName("성공 시 생성된 로또들을 반환한다.")
        @Test
        void successReturnCreatedLottos() {
            //given
            TotalPurchasedLottoCount lottoCount = new TotalPurchasedLottoCount(100);

            //when
            PurchasedLottos lottos = lottoService.getLottos(lottoCount);

            //then
            assertThat(lottos.getLottos()).hasSize(lottoCount.getLottoCount())
                .allSatisfy(lotto -> assertThat(lotto.getNumbers()).hasSize(LottoConstants.NUMBERS_SIZE)
                    .allSatisfy(number -> assertThat(number)
                        .isBetween(LottoConstants.MIN_NUMBER, LottoConstants.MAX_NUMBER)));
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
            BonusBall bonusBall = new BonusBall(9);
            PurchasedLottos lottos = new PurchasedLottos(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 9)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12)),
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),
                new Lotto(List.of(3, 4, 5, 6, 7, 8))
            ));
            PurchaseAmount amount = new PurchaseAmount(1000000);

            //when
            LottoStatistics lottoStatistics = lottoService.calculateStatistics(
                winningLotto, bonusBall, lottos, amount);

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