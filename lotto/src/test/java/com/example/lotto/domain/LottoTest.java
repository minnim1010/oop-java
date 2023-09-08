package com.example.lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Nested
    @DisplayName("새 로또를 생성할 때")
    class CreateLottoConstants {
        @DisplayName("성공 시 새로운 로또를 생성한다.")
        @Test
        void createLotto() {
            //given
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

            //when
            Lotto lotto = new Lotto(numbers);

            //then
            assertThat(lotto.getNumbers()).hasSize(6)
                .isEqualTo(numbers);
        }

        @DisplayName("길이가 6이 아니면 예외가 발생한다.")
        @Test
        void failWithInvalidSize() {
            //given
            List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 6, 7);
            List<Integer> numbers2 = List.of(1, 2, 3, 4);

            //when then
            assertThatThrownBy(() -> new Lotto(numbers1))
                .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> new Lotto(numbers2))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("로또 번호가 1 이상 45 이하가 아니면 예외가 발생한다.")
        @Test
        void failWithOverRangeNumber() {
            //given
            List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 46);
            List<Integer> numbers2 = List.of(0, 1, 2, 3, 4, 5);

            //when then
            assertThatThrownBy(() -> new Lotto(numbers1))
                .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> new Lotto(numbers2))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("로또 번호들이 중복되었다면 예외가 발생한다.")
        @Test
        void failWithDuplicatedNumbers() {
            //given
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

            //when then
            assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("로또 번호들이 오름차순 정렬되어있지 않다면 예외가 발생한다.")
        @Test
        void failWithNonAscendingSorted() {
            //given
            List<Integer> numbers = List.of(1, 2, 3, 4, 7, 5);

            //when then
            assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

}