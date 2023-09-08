package com.example.lotto.vo;

import com.example.lotto.component.WinningRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class LottoStatisticsTest {

    @Nested
    @DisplayName("로또 통계를 생성할 때 ")
    class create {
        @DisplayName("성공 시 생성된 LottoStatistics를 반환한다.")
        @Test
        void successReturnNewLottoStatistics() {
            // given
            WinningRank winningRank = mock(WinningRank.class);
            double profitRate = 1.5;

            // when
            LottoStatistics lottoStatistics = new LottoStatistics(winningRank, profitRate);

            // then
            assertThat(lottoStatistics.getWinningRank()).isEqualTo(winningRank);
            assertThat(lottoStatistics.getProfitRate()).isEqualTo(profitRate);
        }

        @DisplayName("유효하지 않은 WinningRank가 주어지면 예외가 발생한다.")
        @Test
        void failWithInvalidWinningRank() {
            // given
            double profitRate = 1.5;

            // when, then
            assertThatThrownBy(() -> new LottoStatistics(null, profitRate))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("유효하지 않은 profitRate가 주어지면 예외가 발생한다.")
        @Test
        void failWithInvalidProfitRate() {
            // given
            WinningRank winningRank = mock(WinningRank.class);
            double profitRate = -1.0;

            // when, then
            assertThatThrownBy(() -> new LottoStatistics(winningRank, profitRate))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}