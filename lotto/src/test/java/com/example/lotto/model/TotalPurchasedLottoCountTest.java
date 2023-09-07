package com.example.lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TotalPurchasedLottoCountTest {

    @Nested
    @DisplayName("새로운 TotalPurchasedLottoCount를 생성할 때")
    class create {

        @DisplayName("유효한 totalPurchasedLottoCount일 때 생성된 TotalPurchasedLottoCount를 반환한다.")
        @Test
        void successReturnNewTotalPurchasedLottoCount() {
            // given
            int totalPurchasedLottoCount = 10;

            // when
            TotalPurchasedLottoCount purchasedLottoCount = new TotalPurchasedLottoCount(totalPurchasedLottoCount);

            // then
            assertThat(purchasedLottoCount.getLottoCount()).isEqualTo(totalPurchasedLottoCount);
        }

        @DisplayName("유효하지 않은 totalPurchasedLottoCount가 주어지면 예외가 발생한다.")
        @Test
        void failWithInvalidTotalPurchasedLottoCount() {
            // given
            int totalPurchasedLottoCount = 0; // Invalid totalPurchasedLottoCount

            // when, then
            assertThatThrownBy(() -> new TotalPurchasedLottoCount(totalPurchasedLottoCount))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}