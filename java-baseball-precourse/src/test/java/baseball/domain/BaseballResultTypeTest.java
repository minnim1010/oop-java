package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BaseballResultTypeTest {

    static Stream<Arguments> getBaseballResultTypes() {
        return Stream.of(
            Arguments.arguments(true, true, BaseballResultType.STRIKE),
            Arguments.arguments(true, false, BaseballResultType.BALL),
            Arguments.arguments(false, false, BaseballResultType.NOTHING)
        );
    }

    @DisplayName("숫자, 위치 기반으로 올바른 결과를 계산한다.")
    @ParameterizedTest(name = "숫자 매칭: {0}, 위치 매칭: {1}, 결과: {2}")
    @MethodSource("getBaseballResultTypes")
    void findBy(boolean numberMatch, boolean positionMatch, BaseballResultType expected) {
        //given
        //when
        BaseballResultType actual = BaseballResultType.findBy(numberMatch, positionMatch);

        //then
        assertThat(actual).isEqualTo(expected);
    }

}