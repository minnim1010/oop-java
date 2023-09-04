package com.example;

import com.example.lotto.LottoProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoProviderTest {

    private final LottoProvider lottoProvider = new LottoProvider();

    @DisplayName("무작위 로또 숫자 6개를 반환한다.")
    @Test
    void successReturnLottoNumbers() {
        //given

        //when
        List<Integer> lotto = lottoProvider.getLotto();

        //then
        assertThat(lotto).hasSize(6)
            .allSatisfy(elem ->
                assertThat(elem).isLessThanOrEqualTo(45));
    }
}