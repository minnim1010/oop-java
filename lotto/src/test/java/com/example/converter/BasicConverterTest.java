package com.example.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BasicConverterTest {

    @Nested
    @DisplayName("문자열을 int로 바꿀 때 ")
    class convertStringToInt {
        @DisplayName("성공 시 변환된 int를 반환한다.")
        @Test
        void SuccessReturnConvertedInt() {
            //given
            String str = "123";

            //when
            int result = BasicConverter.convertStringToInt(str);

            //then
            assertThat(result).isEqualTo(123);
        }

        @DisplayName("-나 숫자 이외 문자가 포함되었다면 예외가 발생한다.")
        @ValueSource(strings = {"123j", "zxcv", "484*", " ", "", ";"})
        @ParameterizedTest
        void failWithInvalidCharacter(String str) {
            //given

            //when && then
            assertThatThrownBy(() -> BasicConverter.convertStringToInt(str))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("문자열을 Integer형 리스트로 바꿀 때 ")
    class convertStringToIntegerList {
        @DisplayName("성공 시 Integer형 리스트를 반환한다.")
        @Test
        void successReturnIntegerList() {
            //given
            String str = "23, 45, 2, 3, 4, 1";
            List<Integer> expected = List.of(23, 45, 2, 3, 4, 1);

            //when
            List<Integer> result = BasicConverter.convertStringToIntegerList(str);

            //then
            assertThat(result).isEqualTo(expected);
        }

        @DisplayName("구분자가 ', '가 아니라면 예외가 발생한다.")
        @ValueSource(strings = {"1, 2, 3, 4, 5,6", "1.2.3.4.5.6", "1/2/3/4/5/6", "1,2,3,4,5,6"})
        @ParameterizedTest
        void failWithInvalidDelimiter(String str) {
            //given

            //when then
            assertThatThrownBy(() -> BasicConverter.convertStringToIntegerList(str))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("-나 숫자 이외 문자가 포함되었다면 예외가 발생한다.")
        @ValueSource(strings = {"123j, 3", "zxcv, 23", "484*, 45", " ", "", ";"})
        @ParameterizedTest
        void failWithInvalidCharacter(String str) {
            //given

            //when && then
            assertThatThrownBy(() -> BasicConverter.convertStringToIntegerList(str))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}