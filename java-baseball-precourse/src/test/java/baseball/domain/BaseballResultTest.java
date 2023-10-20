package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BaseballResultTest {

    @Nested
    @DisplayName("숫자 야구 게임의 결과 객체 생성 시")
    class create {

        @DisplayName("성공한다.")
        @Test
        void success() {
            //given
            List<BaseballResultType> typeList = List.of(
                BaseballResultType.BALL,
                BaseballResultType.STRIKE,
                BaseballResultType.BALL);

            //when
            BaseballResult baseballResult =
                BaseballResult.create(typeList);

            //then
            assertThat(baseballResult).isNotNull();
            assertThat(baseballResult.getResult())
                .hasSize(2)
                .containsEntry(BaseballResultType.BALL, 2)
                .containsEntry(BaseballResultType.STRIKE, 1);
        }

        @DisplayName("숫자 야구 게임의 결과값이 세 개가 아니라면 생성할 수 없다.")
        @Test
        void fail_invalidInputLength() {
            //given
            List<BaseballResultType> typeList = List.of(
                BaseballResultType.BALL,
                BaseballResultType.STRIKE,
                BaseballResultType.STRIKE,
                BaseballResultType.STRIKE);

            //when then
            assertThatThrownBy(
                () -> BaseballResult.create(typeList))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("숫자 야구의 정답을 맞혔는지 판별할 시")
    class isCorrect {

        @DisplayName("3 strike라면 참을 반환한다.")
        @Test
        void successReturnTrue() {
            //given
            List<BaseballResultType> typeList = List.of(
                BaseballResultType.STRIKE, BaseballResultType.STRIKE, BaseballResultType.STRIKE);
            BaseballResult baseballResult = BaseballResult.create(typeList);

            //when
            boolean result = baseballResult.isCorrect();

            //then
            assertThat(result).isTrue();
        }

        @DisplayName("3 strike가 아니라면 거짓을 반환한다.")
        @Test
        void successReturnFalse() {
            //given
            List<BaseballResultType> typeList = List.of(
                BaseballResultType.BALL, BaseballResultType.STRIKE, BaseballResultType.STRIKE);
            BaseballResult baseballResult = BaseballResult.create(typeList);

            //when
            boolean result = baseballResult.isCorrect();

            //then
            assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("결과 메시지 생성 시")
    class toString {

        static Stream<Arguments> getBaseballResultAndResultMsg() {
            return Stream.of(
                Arguments.of(BaseballResult.create(List.of(
                        BaseballResultType.BALL, BaseballResultType.STRIKE, BaseballResultType.STRIKE)),
                    "1볼 2스트라이크"),
                Arguments.of(BaseballResult.create(List.of(
                        BaseballResultType.BALL, BaseballResultType.BALL, BaseballResultType.BALL)),
                    "3볼"),
                Arguments.of(BaseballResult.create(List.of()),
                    "낫싱")
            );
        }

        @DisplayName("올바른 결과 메시지를 반환한다.")
        @MethodSource("getBaseballResultAndResultMsg")
        @ParameterizedTest(name = "입력: {0} 결과: {1}")
        void success(BaseballResult baseballResult, String expected) {
            //given
            //when
            String result = baseballResult.toString();

            //then
            assertThat(result).isEqualTo(expected);
        }
    }
}