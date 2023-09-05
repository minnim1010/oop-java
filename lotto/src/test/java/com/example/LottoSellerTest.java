package com.example;

import com.example.lotto.service.LottoSeller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoSellerTest {

    private final LottoSeller lottoSeller = new LottoSeller();

    @Nested
    @DisplayName("구매한 금액으로 산 로또 개수를 계산할 때 ")
    class caculatePurchasedLotto {
        @DisplayName("계산 성공 시 살 수 있는 최대 로또 개수를 반환한다.")
        @CsvSource(value= {"15000:15", "1000:1", "14400:14"}, delimiter = ':')
        @ParameterizedTest
        void successReturnPurchasedLottoNum(int money, int expected){
            //given
            //when
            int lotto = lottoSeller.calculatePurchasedLottos(money);

            //then
            assertThat(lotto).isEqualTo(expected);
        }

        @DisplayName("구매 금액이 0 이하라면 예외가 발생한다.")
        @Test
        void failWithPurchaseAmountLessOrEqualZero(){
            //given
            int money = 0;

            //when then
            assertThatThrownBy(() -> lottoSeller.calculatePurchasedLottos(money))
                .isInstanceOf(IllegalStateException.class);
        }
    }

}