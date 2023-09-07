package com.example.lotto.model;

import com.example.lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTicketTest {
    @Nested
    @DisplayName("새로운 WinningLottoTicket을 생성할 때")
    class create {

        @DisplayName("성공 시 생성된 WinningLottoTicket을 반환한다.")
        @Test
        void successReturnNewWinningLottoTicket() {
            // given
            Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            BonusBall bonusBall = new BonusBall(10);

            // when
            WinningLottoTicket winningLottoTicket = new WinningLottoTicket(winningLotto, bonusBall);

            // then
            assertThat(winningLottoTicket.getWinningLotto()).isEqualTo(winningLotto);
            assertThat(winningLottoTicket.getBonusBall()).isEqualTo(bonusBall);
        }

        @DisplayName("winningLotto가 널이라면 예외가 발생한다.")
        @Test
        void failWithInvalidBonusBall() {
            // given
            Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            // when, then
            assertThatThrownBy(() -> new WinningLottoTicket(winningLotto, null))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("보너스볼이 널이라면 예외가 발생한다.")
        @Test
        void failWithInvalidWinningLotto() {
            // given
            BonusBall bonusBall = new BonusBall(10);

            // when, then
            assertThatThrownBy(() -> new WinningLottoTicket(null, bonusBall))
                .isInstanceOf(IllegalArgumentException.class);
        }


        @DisplayName("WinningLotto와 BonusBall 간 중복이 있다면 예외가 발생한다.")
        @Test
        void failWithDuplicatedWinningLottoAndBonusBall() {
            // given
            Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            BonusBall bonusBall = new BonusBall(1);

            // when, then
            assertThatThrownBy(() -> new WinningLottoTicket(winningLotto, bonusBall))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}