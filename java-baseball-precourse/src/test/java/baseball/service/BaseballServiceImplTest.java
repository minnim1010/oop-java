package baseball.service;

import baseball.domain.Baseball;
import baseball.domain.BaseballResult;
import baseball.domain.BaseballResultType;
import baseball.domain.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BaseballServiceImplTest {

    BaseballService service = new BaseballServiceImpl();

    @Nested
    @DisplayName("정답 숫자 야구 생성 시")
    class createRandomBaseball {

        @DisplayName("생성한다.")
        @Test
        void success() {
            //given
            //when
            Baseball baseball = service.createAnswerBaseball();

            //then
            assertThat(baseball).isNotNull();
            assertThat(baseball.getNumbers()).hasSize(Baseball.LENGTH);
        }
    }

    @Nested
    @DisplayName("숫자 야구 게임의 결과 계산 시")
    class calculateResult {

        @DisplayName("성공한다.")
        @Test
        void test() {
            //given
            List<Number> answerNumbers = List.of(
                new Number(1), new Number(2), new Number(3));

            List<Number> guessNumbers = List.of(
                new Number(3), new Number(4), new Number(5));

            Baseball answer = Baseball.create(answerNumbers);
            Baseball guess = Baseball.create(guessNumbers);

            //when
            BaseballResult result = service.calculateResult(answer, guess);

            //then
            assertThat(result).isNotNull();
            assertThat(result.getResult()).isNotNull()
                .containsEntry(BaseballResultType.NOTHING, 2)
                .containsEntry(BaseballResultType.BALL, 1)
                .containsEntry(BaseballResultType.STRIKE, 0);
        }
    }
}