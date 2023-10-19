package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DigitTest {

    @Nested
    @DisplayName("숫자 야구의 숫자 하나 생성 시")
    class create {

        @DisplayName("생성에 성공한다.")
        @Test
        void success() {
            //given
            int num = 1;

            //when
            Digit digit = new Digit(num);

            //then
            assertThat(digit).isNotNull();
            assertThat(digit.getValue()).isEqualTo(num);
        }

        @DisplayName("값이 1 미만이라면 생성할 수 없다.")
        @Test
        void fail_LowerThanRange() {
            //given
            int num = 0;

            //when then
            assertThatThrownBy(() -> new Digit(num))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("값이 9 초과라면 생성할 수 없다.")
        @Test
        void fail_HigherThanRange() {
            //given
            int num = 10;

            //when then
            assertThatThrownBy(() -> new Digit(num))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}