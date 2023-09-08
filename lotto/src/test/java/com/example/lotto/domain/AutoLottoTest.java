package com.example.lotto.domain;

import com.example.lotto.constants.LottoConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AutoLottoTest {
    @DisplayName("자동 로또를 생성할 때 생성한 로또를 반환한다.")
    @Test
    void createAutoLotto() {
        //given

        //when
        Lotto lotto = AutoLotto.create();

        //then
        assertThat(lotto.getNumbers()).hasSize(LottoConstants.NUMBERS_SIZE)
            .allSatisfy(number -> assertThat(number)
                .isBetween(LottoConstants.MIN_NUMBER, LottoConstants.MAX_NUMBER));
    }
}