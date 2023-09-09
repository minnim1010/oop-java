package com.example.lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @DisplayName("일치하는 숫자 개수가 주어지면 순위를 반환한다.")
    @Test
    void from() {
        //given
        int matchCnt = 5;
        boolean hasBonusBall = false;

        //when
        Optional<LottoRank> lottoRank = LottoRank.findByMatchCountAndBonusBall(matchCnt, hasBonusBall);

        //then
        assertThat(lottoRank.orElse(null)).isEqualTo(LottoRank.THIRD);
    }
}