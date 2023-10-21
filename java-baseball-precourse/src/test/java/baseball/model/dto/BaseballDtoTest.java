package baseball.model.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import baseball.model.Baseball;
import baseball.model.BaseballNumber;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BaseballDtoTest {

    @Nested
    @DisplayName("BaseballDto를 생성할 시")
    class create {

        @DisplayName("성공적으로 생성한다.")
        @ValueSource(strings = {"123", "345", "937"})
        @ParameterizedTest(name = "입력: {0}")
        void success(String input) {
            //given
            //when
            BaseballDto baseballDto = new BaseballDto(input);

            //then
            assertThat(baseballDto).isNotNull();
            assertThat(baseballDto.getBaseball()).isEqualTo(input);
        }

        @DisplayName("길이가 3이 아니라면 예외가 발생한다.")
        @ValueSource(strings = {"", "45", "1234"})
        @ParameterizedTest(name = "입력: {0}")
        void fail_InvalidLength(String input) {
            //given
            //when then
            assertThatThrownBy(() -> new BaseballDto(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("1-9가 아닌 문자가 포함되었다면 예외가 발생한다.")
        @ValueSource(strings = {"   ", "\n\n\n", "12#", "093", "^@$", "ABC"})
        @ParameterizedTest(name = "입력: {0}")
        void fail_InvalidCharacter(String input) {
            //given
            //when then
            assertThatThrownBy(() -> new BaseballDto(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("중복된 숫자가 있다면 예외가 발생한다.")
        @ValueSource(strings = {"221", "122", "212", "333"})
        @ParameterizedTest(name = "입력: {0}")
        void fail_DuplicatedCharacter(String input) {
            //given
            //when then
            assertThatThrownBy(() -> new BaseballDto(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("BaseballDto를 Baseball 타입으로 변환 시")
    class toBaseball {

        @DisplayName("성공적으로 변환된다.")
        @Test
        void success() {
            //given
            String baseballInput = "987";
            BaseballDto baseballDto = new BaseballDto(baseballInput);

            //when
            Baseball baseball = baseballDto.toBaseball();

            //then
            assertThat(baseball).isNotNull();
            assertThat(baseball.getNumbers())
                    .extracting(BaseballNumber::getNumber)
                    .containsExactlyElementsOf(List.of(9, 8, 7));
        }
    }
}