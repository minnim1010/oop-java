package baseball.service;

import baseball.constants.BaseballGame;
import baseball.domain.BaseballNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class BaseballServiceImplTest {

    BaseballService service = new BaseballServiceImpl();

    @Nested
    @DisplayName("정답 숫자 야구 생성 시")
    class createRandomBaseballNumber {
        @DisplayName("생성한다.")
        @Test
        void success(){
            //given
            //when
            BaseballNumber baseballNumber = service.createRandomBaseballNumber();

            //then
            assertThat(baseballNumber).isNotNull();
            assertThat(baseballNumber.getDigits()).hasSize(BaseballGame.BASEBALL_NUMBER_LENGTH);
        }
    }

}