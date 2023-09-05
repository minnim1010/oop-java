package com.example.lotto;

import com.example.lotto.domain.Lotto;
import com.example.lotto.service.LottoProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoProviderTest {

    private final LottoProvider lottoProvider = new LottoProvider();

    @DisplayName("로또 개수가 주어지면 숫자 6개를 무작위로 뽑은 로또를 주어진 개수만큼 반환한다.")
    @Test
    void successReturnLottos() {
        //given
        int lottoSize = 3;

        //when
        List<Lotto> lottos = lottoProvider.getLottos(lottoSize);

        //then
        assertThat(lottos).hasSize(lottoSize)
            .allSatisfy(lotto ->
                assertThat(lotto.getNumbers()).hasSize(LottoProvider.LOTTO_SIZE)
                    .allSatisfy(number ->
                        assertThat(number).isLessThanOrEqualTo(LottoProvider.LOTTO_MAX_NUMBER)));
    }
}