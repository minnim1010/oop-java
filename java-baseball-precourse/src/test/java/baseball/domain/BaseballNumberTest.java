package baseball.domain;

import baseball.constants.BaseballGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
            Digit digit1 = new Digit(1);
            Digit digit2 = new Digit(2);
            Digit digit3 = new Digit(3);

            //when
            BaseballNumber baseballNumber =
                BaseballNumber.create(new Digit[]{digit1, digit2, digit3});

            //then
            assertThat(baseballNumber).isNotNull();
            assertThat(baseballNumber.getDigits())
                .hasSize(BaseballGame.BASEBALL_NUMBER_LENGTH)
                .containsExactly(digit1, digit2, digit3);
        }

        @DisplayName("숫자가 세 개 미만으로 주어진다면 생성할 수 없다.")
        @Test
        void fail_ShortLength(){
            //given
            Digit digit1 = new Digit(1);
            Digit digit2 = new Digit(2);

            //when then
            assertThatThrownBy(
                () -> BaseballNumber.create(new Digit[]{digit1, digit2}))
                .isInstanceOf(IllegalStateException.class);
        }

        @DisplayName("숫자가 세 개 초과로 주어진다면 생성할 수 없다.")
        @Test
        void fail_LongLength(){
            //given
            Digit digit1 = new Digit(1);
            Digit digit2 = new Digit(2);
            Digit digit3 = new Digit(3);
            Digit digit4 = new Digit(4);

            //when then
            assertThatThrownBy(
                () -> BaseballNumber.create(new Digit[]{digit1, digit2, digit3, digit4}))
                .isInstanceOf(IllegalStateException.class);
        }

        @DisplayName("숫자가 중복된다면 생성할 수 없다.")
        @Test
        void test(){
            //given
            Digit digit1 = new Digit(1);
            Digit digit2 = new Digit(2);
            Digit digit3 = new Digit(2);

            //when then
            assertThatThrownBy(
                () -> BaseballNumber.create(new Digit[]{digit1, digit2, digit3}))
                .isInstanceOf(IllegalStateException.class);
        }
    }
}