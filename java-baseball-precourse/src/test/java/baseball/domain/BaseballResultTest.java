package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BaseballResultTest {
    @Nested
    @DisplayName("숫자 야구 게임의 결과 객체 생성 시")
    class create {
        @DisplayName("성공한다.")
        @Test
        void success(){
            //given
            BaseballResultType ball = BaseballResultType.BALL;
            BaseballResultType strike = BaseballResultType.STRIKE;
            BaseballResultType nothing = BaseballResultType.NOTHING;

            //when
            BaseballResult baseballResult =
                BaseballResult.create(new BaseballResultType[]{ball, strike, nothing});

            //then
            assertThat(baseballResult).isNotNull();
            assertThat(baseballResult.getResult().values())
                .hasSize(3)
                .containsExactly(1, 1, 1);
        }

        @DisplayName("숫자 야구 게임의 결과값이 세 개가 아니라면 생성할 수 없다.")
        @Test
        void fail_invalidInputLength(){
            //given
            BaseballResultType ball = BaseballResultType.BALL;
            BaseballResultType strike = BaseballResultType.STRIKE;
            BaseballResultType nothing = BaseballResultType.NOTHING;

            //when then
            assertThatThrownBy(
                () -> BaseballResult.create(
                    new BaseballResultType[]{ball, strike, nothing, nothing}))
                .isInstanceOf(IllegalStateException.class);
        }
    }
}