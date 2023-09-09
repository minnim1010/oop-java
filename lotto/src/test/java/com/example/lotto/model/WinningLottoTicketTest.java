package com.example.lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTicketTest {
    @DisplayName("로또가 주어지면 당첨 로또와 비교해 일치하는 숫자를 반환한다.")
    @Test
    void countMatchNumber() {
        //given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusBall = 10;

        WinningLottoTicket winningLottoTicket = new WinningLottoTicket(winningLotto, bonusBall);
        //when
        int cnt = winningLottoTicket.countMatchNumber(lotto);

        //then
        assertThat(cnt).isEqualTo(5);
    }

    @Nested
    @DisplayName("새로운 WinningLottoTicket을 생성할 때")
    class create {

        @DisplayName("성공 시 생성된 WinningLottoTicket을 반환한다.")
        @Test
        void successReturnNewWinningLottoTicket() {
            // given
            Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            int bonusBall = 10;

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
            int bonusBall = 1;

            // when, then
            assertThatThrownBy(() -> new WinningLottoTicket(null, bonusBall))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("WinningLotto와 BonusBall 간 중복이 있다면 예외가 발생한다.")
        @Test
        void failWithDuplicatedWinningLottoAndBonusBall() {
            // given
            Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            int bonusBall = 1;

            // when, then
            assertThatThrownBy(() -> new WinningLottoTicket(winningLotto, bonusBall))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }
}