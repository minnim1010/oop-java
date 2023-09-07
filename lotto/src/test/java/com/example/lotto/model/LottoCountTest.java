package com.example.lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoCountTest {

    @Nested
    @DisplayName("새로운 lottoCount를 생성할 때")
    class create {

        @DisplayName("로또 개수가 0 이상이면 생성된 LottoCount를 반환한다.")
        @Test
        void successReturnNewTotalPurchasedLottoCount() {
            // given
            int cnt = 10;

            // when
            LottoCount lottoCount = new LottoCount(cnt);

            // then
            assertThat(lottoCount.getLottoCount()).isEqualTo(cnt);
        }

        @DisplayName("음수가 주어지면 예외가 발생한다.")
        @Test
        void failWithInvalidLottoCount() {
            // given
            int lottoCount = -1;

            // when, then
            assertThatThrownBy(() -> new LottoCount(lottoCount))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}