package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BaseballNumberTest {

    @Nested
    @DisplayName("숫자 야구의 게임에 사용되는 세 숫자 생성 시")
    class create {
        @DisplayName("생성에 성공한다.")
        @Test
        void success() {
            //given
            List<Digit> digitList = List.of(
                new Digit(1), new Digit(2), new Digit(3));

            //when
            BaseballNumber baseballNumber =
                BaseballNumber.create(digitList);

            //then
            assertThat(baseballNumber).isNotNull();
            assertThat(baseballNumber.getDigits())
                .hasSize(BaseballNumber.BASEBALL_NUMBER_LENGTH)
                .containsAll(digitList);
        }

        @DisplayName("숫자가 세 개 미만으로 주어진다면 생성할 수 없다.")
        @Test
        void fail_ShortLength(){
            //given
            List<Digit> digitList = List.of(new Digit(1), new Digit(2));

            //when then
            assertThatThrownBy(
                () -> BaseballNumber.create(digitList))
                .isInstanceOf(IllegalStateException.class);
        }

        @DisplayName("숫자가 세 개 초과로 주어진다면 생성할 수 없다.")
        @Test
        void fail_LongLength(){
            //given
            List<Digit> digitList = List.of(
                new Digit(1), new Digit(2), new Digit(3), new Digit(4));

            //when then
            assertThatThrownBy(
                () -> BaseballNumber.create(digitList))
                .isInstanceOf(IllegalStateException.class);
        }

        @DisplayName("숫자가 중복된다면 생성할 수 없다.")
        @Test
        void test(){
            //given
            List<Digit> digitList = List.of(
                new Digit(1), new Digit(2), new Digit(2));

            //when then
            assertThatThrownBy(
                () -> BaseballNumber.create(digitList))
                .isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    @DisplayName("숫자 야구 2개를 비교할 시")
    class match {

        static Stream<Arguments> getTwoBaseballNumbersAndResult() {
            return Stream.of(
                Arguments.of(
                    BaseballNumber.create(List.of(new Digit(1), new Digit(2), new Digit(3))),
                    BaseballNumber.create(List.of(new Digit(1), new Digit(2), new Digit(3))),
                    List.of(BaseballResultType.STRIKE, BaseballResultType.STRIKE, BaseballResultType.STRIKE)),
                Arguments.of(
                    BaseballNumber.create(List.of(new Digit(4), new Digit(2), new Digit(5))),
                    BaseballNumber.create(List.of(new Digit(2), new Digit(5), new Digit(4))),
                    List.of(BaseballResultType.BALL, BaseballResultType.BALL, BaseballResultType.BALL)),
                Arguments.of(
                    BaseballNumber.create(List.of(new Digit(6), new Digit(8), new Digit(9))),
                    BaseballNumber.create(List.of(new Digit(2), new Digit(3), new Digit(9))),
                    List.of(BaseballResultType.NOTHING, BaseballResultType.NOTHING, BaseballResultType.STRIKE)),
                Arguments.of(
                    BaseballNumber.create(List.of(new Digit(1), new Digit(2), new Digit(4))),
                    BaseballNumber.create(List.of(new Digit(3), new Digit(4), new Digit(2))),
                    List.of(BaseballResultType.NOTHING, BaseballResultType.BALL, BaseballResultType.BALL)),
                Arguments.of(
                    BaseballNumber.create(List.of(new Digit(3), new Digit(2), new Digit(1))),
                    BaseballNumber.create(List.of(new Digit(1), new Digit(2), new Digit(3))),
                    List.of(BaseballResultType.BALL, BaseballResultType.STRIKE, BaseballResultType.BALL))
            );
        }

        @DisplayName("올바른 결과를 반환한다.")
        @ParameterizedTest(name = "{0} {1} {2}")
        @MethodSource("getTwoBaseballNumbersAndResult")
        void success(BaseballNumber answer, BaseballNumber guess, List<BaseballResultType> expected) {
            //given
            //when
            List<BaseballResultType> result = answer.match(guess);

            //then
            assertThat(result).isNotNull()
                .hasSize(3)
                .containsExactlyElementsOf(expected);
        }
    }
}