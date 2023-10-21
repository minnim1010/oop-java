package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import baseball.constants.BaseballResultType;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BaseballTest {

    @Nested
    @DisplayName("숫자 야구의 게임에 사용되는 세 숫자 생성 시")
    class create {

        @DisplayName("생성에 성공한다.")
        @Test
        void success() {
            //given
            List<BaseballNumber> baseballNumberList = List.of(
                    BaseballNumber.create(1), BaseballNumber.create(2), BaseballNumber.create(3));

            //when
            Baseball baseball =
                    Baseball.create(baseballNumberList);

            //then
            assertThat(baseball).isNotNull();
            assertThat(baseball.getNumbers())
                    .hasSize(Baseball.LENGTH)
                    .containsAll(baseballNumberList);
        }

        @DisplayName("숫자가 세 개 미만으로 주어진다면 생성할 수 없다.")
        @Test
        void fail_ShortLength() {
            //given
            List<BaseballNumber> baseballNumberList = List.of(BaseballNumber.create(1),
                    BaseballNumber.create(2));

            //when then
            assertThatThrownBy(
                    () -> Baseball.create(baseballNumberList))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("숫자가 세 개 초과로 주어진다면 생성할 수 없다.")
        @Test
        void fail_LongLength() {
            //given
            List<BaseballNumber> baseballNumberList = List.of(
                    BaseballNumber.create(1), BaseballNumber.create(2), BaseballNumber.create(3),
                    BaseballNumber.create(4));

            //when then
            assertThatThrownBy(
                    () -> Baseball.create(baseballNumberList))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("숫자가 중복된다면 생성할 수 없다.")
        @Test
        void test() {
            //given
            List<BaseballNumber> baseballNumberList = List.of(
                    BaseballNumber.create(1), BaseballNumber.create(2), BaseballNumber.create(2));

            //when then
            assertThatThrownBy(
                    () -> Baseball.create(baseballNumberList))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("숫자 야구 2개를 비교할 시")
    class match {

        static Stream<Arguments> getTwoBaseballsAndResult() {
            return Stream.of(
                    Arguments.of(
                            Baseball.create(List.of(BaseballNumber.create(1), BaseballNumber.create(2),
                                    BaseballNumber.create(3))),
                            Baseball.create(List.of(BaseballNumber.create(1), BaseballNumber.create(2),
                                    BaseballNumber.create(3))), 0, 3),
                    Arguments.of(
                            Baseball.create(List.of(BaseballNumber.create(4), BaseballNumber.create(2),
                                    BaseballNumber.create(5))),
                            Baseball.create(List.of(BaseballNumber.create(2), BaseballNumber.create(5),
                                    BaseballNumber.create(4))), 3, 0),
                    Arguments.of(
                            Baseball.create(List.of(BaseballNumber.create(6), BaseballNumber.create(8),
                                    BaseballNumber.create(9))),
                            Baseball.create(List.of(BaseballNumber.create(2), BaseballNumber.create(3),
                                    BaseballNumber.create(9))), 0, 1),
                    Arguments.of(
                            Baseball.create(List.of(BaseballNumber.create(1), BaseballNumber.create(2),
                                    BaseballNumber.create(4))),
                            Baseball.create(List.of(BaseballNumber.create(3), BaseballNumber.create(4),
                                    BaseballNumber.create(2))), 2, 0),
                    Arguments.of(
                            Baseball.create(List.of(BaseballNumber.create(3), BaseballNumber.create(2),
                                    BaseballNumber.create(1))),
                            Baseball.create(List.of(BaseballNumber.create(1), BaseballNumber.create(2),
                                    BaseballNumber.create(3))), 2, 1)
            );
        }

        @DisplayName("올바른 결과를 반환한다.")
        @ParameterizedTest(name = "{0} {1} ball: {2} strike: {3}")
        @MethodSource("getTwoBaseballsAndResult")
        void success(Baseball answer, Baseball guess, int ballCount, int strikeCount) {
            //given
            //when
            BaseballResult result = answer.match(guess);

            //then
            assertThat(result).isNotNull();
            assertThat(result.getResult())
                    .containsEntry(BaseballResultType.BALL, ballCount)
                    .containsEntry(BaseballResultType.STRIKE, strikeCount);
        }
    }
}