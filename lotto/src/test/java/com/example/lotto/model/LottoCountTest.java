package com.example.lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoCountTest {

    @Nested
    @DisplayName("새로운 lottoCount를 생성할 때")
    class create {

        @DisplayName("로또 개수가 0 이상이면 생성된 LottoCount를 반환한다.")
        @Test
        void successReturnNewLottoCount() {
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

    @Nested
    @DisplayName("금액을 기반으로 LottoCount를 생성할 때 ")
    class of {
        @DisplayName("살 수 있는 최대 로또 개수를 가진 LottoCount를 반환한다.")
        @CsvSource(value = {"15000:15", "1000:1", "14400:14"}, delimiter = ':')
        @ParameterizedTest
        void successReturnPurchasedLottoCount(int amount, int expected) {
            //given
            //when
            LottoCount lottoCount = LottoCount.of(new PurchaseAmount(amount));

            //then
            assertThat(lottoCount.getLottoCount()).isEqualTo(expected);
        }
    }
}