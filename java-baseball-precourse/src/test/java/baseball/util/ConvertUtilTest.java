package baseball.util;

import baseball.domain.BaseballNumber;
import baseball.domain.Digit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConvertUtilTest {

    @Nested
    @DisplayName("String 타입에서 BaseballNumber 타입으로 변경 시")
    class toBaseballNumber {

        static Stream<Arguments> getBaseballNumber() {
            return Stream.of(
                Arguments.of("123", 1, 2, 3),
                Arguments.of("234", 2, 3, 4),
                Arguments.of("054", 0, 5, 4),
                Arguments.of("946", 9, 4, 6)
            );
        }

        @DisplayName("성공한다.")
        @MethodSource("getBaseballNumber")
        @ParameterizedTest(name = "입력: {0}")
        void success(String input, int num1, int num2, int num3) {
            //given
            //when
            BaseballNumber baseballNumber = ConvertUtil.toBaseballNumber(input);

            //then
            assertThat(baseballNumber).isNotNull();
            assertThat(baseballNumber.getDigits().stream()
                .map(Digit::getValue)
                .collect(Collectors.toList()))
                .hasSize(3)
                .containsExactly(num1, num2, num3);
        }

        @DisplayName("실패한다.")
        @ValueSource(strings = {"ㄴㅇㄱ", "...", "", "   ", "12"})
        @ParameterizedTest(name = "입력: {0}")
        void fail(String input) {
            //given
            //when then
            assertThatThrownBy(() -> ConvertUtil.toBaseballNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}