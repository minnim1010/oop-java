package com.example.lotto.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusBallTest {

    @Nested
    @DisplayName("새로운 BonusBall을 생성할 때 ")
    class create {
        @DisplayName("성공 시 생성된 BonusBall을 반환한다.")
        @Test
        void successReturnNewBonusBall() {
            //given
            int number = 12;

            //when
            BonusBall bonusBall = new BonusBall(number);
            //then
            assertThat(bonusBall.getNumber()).isEqualTo(number);
        }

        @DisplayName("유효하지 않은 숫자 범위라면 예외가 발생한다.")
        @ValueSource(ints = {0, 46})
        @ParameterizedTest
        void failWithInvalidNumberRange(int number) {
            //given

            //when then
            assertThatThrownBy(() -> new BonusBall(number))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}