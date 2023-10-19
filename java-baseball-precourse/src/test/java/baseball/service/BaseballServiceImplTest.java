package baseball.service;

import baseball.constants.BaseballGame;
import baseball.domain.BaseballNumber;
import baseball.domain.BaseballResult;
import baseball.domain.BaseballResultType;
import baseball.domain.Digit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BaseballServiceImplTest {

    BaseballService service = new BaseballServiceImpl();

    @Nested
    @DisplayName("정답 숫자 야구 생성 시")
    class createRandomBaseballNumber {

        @DisplayName("생성한다.")
        @Test
        void success() {
            //given
            //when
            BaseballNumber baseballNumber = service.createRandomBaseballNumber();

            //then
            assertThat(baseballNumber).isNotNull();
            assertThat(baseballNumber.getDigits()).hasSize(BaseballGame.BASEBALL_NUMBER_LENGTH);
        }
    }

    @Nested
    @DisplayName("숫자 야구 게임의 결과 계산 시")
    class calculateResult {

        @DisplayName("성공한다.")
        @Test
        void test() {
            //given
            List<Digit> answerDigits = new ArrayList<>();
            answerDigits.add(new Digit(1));
            answerDigits.add(new Digit(2));
            answerDigits.add(new Digit(3));

            List<Digit> guessDigits = new ArrayList<>();
            guessDigits.add(new Digit(3));
            guessDigits.add(new Digit(4));
            guessDigits.add(new Digit(5));

            BaseballNumber answer = BaseballNumber.create(answerDigits);
            BaseballNumber guess = BaseballNumber.create(guessDigits);

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