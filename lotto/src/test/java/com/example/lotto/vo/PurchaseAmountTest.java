package com.example.lotto.vo;

import com.example.lotto.constants.LottoConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {

    @Nested
    @DisplayName("구매 금액을 생성할 때 ")
    class create {
        @DisplayName("성공 시 생성된 구매 금액을 반환한다.")
        @Test
        void ReturnNewPurchaseAmount() {
            //given

            //when
            PurchaseAmount purchaseAmount = new PurchaseAmount(1000);

            //then
            assertThat(purchaseAmount.getAmount()).isEqualTo(1000);
        }

        @DisplayName("로또 하나 가격보다 낮은 금액인 경우 예외가 발생한다.")
        @Test
        void failWithLowerAmountThanALottoPrice() {
            //given

            //when then
            assertThatThrownBy(() -> new PurchaseAmount(LottoConstants.PRICE - 1))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}