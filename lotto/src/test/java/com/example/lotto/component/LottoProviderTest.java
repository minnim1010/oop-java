package com.example.lotto.component;

import com.example.lotto.constants.LottoConstants;
import com.example.lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoProviderTest {

    private final LottoProvider lottoProvider = new LottoProvider();

    @DisplayName("숫자 6개를 무작위로 뽑은 로또를 반환한다.")
    @Test
    void createLotto() {
        //given

        //when
        Lotto lotto = lottoProvider.createLotto();

        //then
        assertThat(lotto.getNumbers()).hasSize(LottoConstants.NUMBERS_SIZE)
            .allSatisfy(number -> assertThat(number)
                .isBetween(LottoConstants.MIN_NUMBER, LottoConstants.MAX_NUMBER));
    }
}