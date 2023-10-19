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

class BaseballTest {

    @Nested
    @DisplayName("숫자 야구의 게임에 사용되는 세 숫자 생성 시")
    class create {
        @DisplayName("생성에 성공한다.")
        @Test
        void success() {
            //given
            List<Number> numberList = List.of(
                new Number(1), new Number(2), new Number(3));

            //when
            Baseball baseball =
                Baseball.create(numberList);

            //then
            assertThat(baseball).isNotNull();
            assertThat(baseball.getNumbers())
                .hasSize(Baseball.LENGTH)
                .containsAll(numberList);
        }

        @DisplayName("숫자가 세 개 미만으로 주어진다면 생성할 수 없다.")
        @Test
        void fail_ShortLength() {
            //given
            List<Number> numberList = List.of(new Number(1), new Number(2));

            //when then
            assertThatThrownBy(
                () -> Baseball.create(numberList))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("숫자가 세 개 초과로 주어진다면 생성할 수 없다.")
        @Test
        void fail_LongLength() {
            //given
            List<Number> numberList = List.of(
                new Number(1), new Number(2), new Number(3), new Number(4));

            //when then
            assertThatThrownBy(
                () -> Baseball.create(numberList))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("숫자가 중복된다면 생성할 수 없다.")
        @Test
        void test() {
            //given
            List<Number> numberList = List.of(
                new Number(1), new Number(2), new Number(2));

            //when then
            assertThatThrownBy(
                () -> Baseball.create(numberList))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("숫자 야구 2개를 비교할 시")
    class match {

        static Stream<Arguments> getTwoBaseballsAndResult() {
            return Stream.of(
                Arguments.of(
                    Baseball.create(List.of(new Number(1), new Number(2), new Number(3))),
                    Baseball.create(List.of(new Number(1), new Number(2), new Number(3))),
                    List.of(BaseballResultType.STRIKE, BaseballResultType.STRIKE, BaseballResultType.STRIKE)),
                Arguments.of(
                    Baseball.create(List.of(new Number(4), new Number(2), new Number(5))),
                    Baseball.create(List.of(new Number(2), new Number(5), new Number(4))),
                    List.of(BaseballResultType.BALL, BaseballResultType.BALL, BaseballResultType.BALL)),
                Arguments.of(
                    Baseball.create(List.of(new Number(6), new Number(8), new Number(9))),
                    Baseball.create(List.of(new Number(2), new Number(3), new Number(9))),
                    List.of(BaseballResultType.NOTHING, BaseballResultType.NOTHING, BaseballResultType.STRIKE)),
                Arguments.of(
                    Baseball.create(List.of(new Number(1), new Number(2), new Number(4))),
                    Baseball.create(List.of(new Number(3), new Number(4), new Number(2))),
                    List.of(BaseballResultType.NOTHING, BaseballResultType.BALL, BaseballResultType.BALL)),
                Arguments.of(
                    Baseball.create(List.of(new Number(3), new Number(2), new Number(1))),
                    Baseball.create(List.of(new Number(1), new Number(2), new Number(3))),
                    List.of(BaseballResultType.BALL, BaseballResultType.STRIKE, BaseballResultType.BALL))
            );
        }

        @DisplayName("올바른 결과를 반환한다.")
        @ParameterizedTest(name = "{0} {1} {2}")
        @MethodSource("getTwoBaseballsAndResult")
        void success(Baseball answer, Baseball guess, List<BaseballResultType> expected) {
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