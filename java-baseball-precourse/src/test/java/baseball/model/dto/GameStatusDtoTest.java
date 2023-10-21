package baseball.model.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import baseball.constants.GameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GameStatusDtoTest {

    @Nested
    @DisplayName("GameStatusDto 생성 시")
    class create {

        @DisplayName("성공적으로 생성한다.")
        @ValueSource(strings = {"1", "2"})
        @ParameterizedTest(name = "입력: {0}")
        void success(String input) {
            //given
            //when
            GameStatusDto gameStatusDto = new GameStatusDto(input);

            //then
            assertThat(gameStatusDto.getStatus()).isEqualTo(input);
        }

        @DisplayName("1,2가 아닌 경우 예외가 발생한다.")
        @ValueSource(strings = {"\n", " ", "", "NULL", "0", "3"})
        @ParameterizedTest(name = "입력: {0}")
        void fail_invalidCharacter(String input) {
            //given
            //when then
            assertThatThrownBy(() -> new GameStatusDto(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("GameStatusDto가 재시작 상태인지 확인 시")
    class isRestart {

        @DisplayName("status가 1이라면 참을 반환한다.")
        @Test
        void returnTrue() {
            //given
            GameStatusDto gameStatusDto = new GameStatusDto(GameStatus.RESTART.getCode());

            //when
            boolean restart = gameStatusDto.isRestart();

            //then
            assertThat(restart).isTrue();
        }

        @DisplayName("status가 2이라면 거짓을 반환한다.")
        @Test
        void returnFalse() {
            //given
            GameStatusDto gameStatusDto = new GameStatusDto(GameStatus.END.getCode());

            //when
            boolean restart = gameStatusDto.isRestart();

            //then
            assertThat(restart).isFalse();
        }
    }

}