package baseball.domain;

import baseball.constants.BaseballGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
            List<Digit> digitList = new ArrayList<>();
            digitList.add(new Digit(1));
            digitList.add(new Digit(2));
            digitList.add(new Digit(3));

            //when
            BaseballNumber baseballNumber =
                BaseballNumber.create(digitList);

            //then
            assertThat(baseballNumber).isNotNull();
            assertThat(baseballNumber.getDigits())
                .hasSize(BaseballGame.BASEBALL_NUMBER_LENGTH)
                .containsAll(digitList);
        }

        @DisplayName("숫자가 세 개 미만으로 주어진다면 생성할 수 없다.")
        @Test
        void fail_ShortLength(){
            //given
            List<Digit> digitList = new ArrayList<>();
            digitList.add(new Digit(1));
            digitList.add(new Digit(2));

            //when then
            assertThatThrownBy(
                () -> BaseballNumber.create(digitList))
                .isInstanceOf(IllegalStateException.class);
        }

        @DisplayName("숫자가 세 개 초과로 주어진다면 생성할 수 없다.")
        @Test
        void fail_LongLength(){
            //given
            List<Digit> digitList = new ArrayList<>();
            digitList.add(new Digit(1));
            digitList.add(new Digit(2));
            digitList.add(new Digit(3));
            digitList.add(new Digit(4));

            //when then
            assertThatThrownBy(
                () -> BaseballNumber.create(digitList))
                .isInstanceOf(IllegalStateException.class);
        }

        @DisplayName("숫자가 중복된다면 생성할 수 없다.")
        @Test
        void test(){
            //given
            List<Digit> digitList = new ArrayList<>();
            digitList.add(new Digit(1));
            digitList.add(new Digit(2));
            digitList.add(new Digit(2));

            //when then
            assertThatThrownBy(
                () -> BaseballNumber.create(digitList))
                .isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    @DisplayName("숫자 야구 2개를 비교할 시")
    class compareTo {
        @DisplayName("올바른 결과를 반환한다.")
        @Test
        void success() {
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
            List<BaseballResultType> result = answer.compareTo(guess);

            //then
            assertThat(result).isNotNull()
                .hasSize(3)
                .containsExactly(
                    BaseballResultType.NOTHING,
                    BaseballResultType.NOTHING,
                    BaseballResultType.BALL);
        }
    }
}