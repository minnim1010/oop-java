package baseball.service;

import static org.assertj.core.api.Assertions.assertThat;

import baseball.domain.Baseball;
import baseball.domain.BaseballNumber;
import baseball.domain.BaseballResult;
import baseball.domain.BaseballResultType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BaseballServiceTest {

    BaseballService service = new BaseballServiceImpl();

    @Nested
    @DisplayName("정답 숫자 야구 생성 시")
    class createRandomBaseball {

        @DisplayName("중복 없는 숫자를 가진 숫자 야구를 생성한다.")
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

        @DisplayName("올바른 계산 결과를 반환한다.")
        @Test
        void success() {
            //given
            List<BaseballNumber> answerBaseballNumbers = List.of(
                BaseballNumber.create(1), BaseballNumber.create(2), BaseballNumber.create(3));

            List<BaseballNumber> guessBaseballNumbers = List.of(
                BaseballNumber.create(3), BaseballNumber.create(4), BaseballNumber.create(5));

            Baseball answer = Baseball.create(answerBaseballNumbers);
            Baseball guess = Baseball.create(guessBaseballNumbers);

            //when
            BaseballResult result = service.calculateResult(answer, guess);

            //then
            assertThat(result).isNotNull();
            assertThat(result.getResult()).isNotNull()
                .containsEntry(BaseballResultType.BALL, 1)
                .containsEntry(BaseballResultType.STRIKE, 0);
        }
    }
}