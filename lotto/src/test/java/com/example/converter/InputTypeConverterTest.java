package com.example.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputTypeConverterTest {

    @Nested
    @DisplayName("String 타입을 int 타입으로 바꿀 때 ")
    class convertStringToInt {
        @DisplayName("성공 시 변환된 int를 반환한다.")
        @Test
        void SuccessReturnConvertedInt() {
            //given
            String str = "123";

            //when
            int result = InputTypeConverter.convertStringToInt(str);

            //then
            assertThat(result).isEqualTo(123);
        }

        @DisplayName("-나 숫자 이외 문자가 포함되었다면 예외가 발생한다.")
        @ValueSource(strings = {"123j", "zxcv", "484*", " ", "", ";"})
        @ParameterizedTest
        void failWithInvalidCharacter(String str) {
            //given

            //when && then
            assertThatThrownBy(() -> InputTypeConverter.convertStringToInt(str))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }


}