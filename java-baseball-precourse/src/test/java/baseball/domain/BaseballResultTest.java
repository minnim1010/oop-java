package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
            List<BaseballResultType> typeList = new ArrayList<>();
            typeList.add(BaseballResultType.BALL);
            typeList.add(BaseballResultType.STRIKE);
            typeList.add(BaseballResultType.NOTHING);

            //when
            BaseballResult baseballResult =
                BaseballResult.create(typeList);

            //then
            assertThat(baseballResult).isNotNull();
            assertThat(baseballResult.getResult())
                .hasSize(3)
                .containsEntry(BaseballResultType.NOTHING, 1)
                .containsEntry(BaseballResultType.BALL, 1)
                .containsEntry(BaseballResultType.STRIKE, 1);
        }

        @DisplayName("숫자 야구 게임의 결과값이 세 개가 아니라면 생성할 수 없다.")
        @Test
        void fail_invalidInputLength(){
            //given
            List<BaseballResultType> typeList = new ArrayList<>();
            typeList.add(BaseballResultType.BALL);
            typeList.add(BaseballResultType.STRIKE);
            typeList.add(BaseballResultType.NOTHING);
            typeList.add(BaseballResultType.NOTHING);

            //when then
            assertThatThrownBy(
                () -> BaseballResult.create(typeList))
                .isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    @DisplayName("숫자 야구 결과의 정답 여부를 반환할 시")
    class isCorrect {
        @DisplayName("정답이라면 참을 반환한다.")
        @Test
        void successReturnTrue() {
            //given
            List<BaseballResultType> typeList = new ArrayList<>();
            typeList.add(BaseballResultType.STRIKE);
            typeList.add(BaseballResultType.STRIKE);
            typeList.add(BaseballResultType.STRIKE);

            BaseballResult baseballResult = BaseballResult.create(typeList);

            //when
            boolean result = baseballResult.isCorrect();

            //then
            assertThat(result).isTrue();
        }

        @DisplayName("정답이 아니라면 거짓을 반환한다.")
        @Test
        void successReturnFalse() {
            //given
            List<BaseballResultType> typeList = new ArrayList<>();
            typeList.add(BaseballResultType.BALL);
            typeList.add(BaseballResultType.STRIKE);
            typeList.add(BaseballResultType.STRIKE);

            BaseballResult baseballResult = BaseballResult.create(typeList);

            //when
            boolean result = baseballResult.isCorrect();

            //then
            assertThat(result).isFalse();
        }
    }
}